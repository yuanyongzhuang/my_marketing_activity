package com.marketing.activity.service.c.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.base.RedisLock;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.constant.RedisKeyConstant;
import com.marketing.activity.domain.bo.VoucherDataResult;
import com.marketing.activity.domain.bo.VoucherText;
import com.marketing.activity.domain.dto.ExamDirectoryInfoDTO;
import com.marketing.activity.domain.dto.UserInfoDTO;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.domain.param.ExamGroupPickVoucherParam;
import com.marketing.activity.domain.param.ReceiveVoucherParam;
import com.marketing.activity.domain.resp.ExamGroupPickVoucherResp;
import com.marketing.activity.domain.resp.ExamGroupResp;
import com.marketing.activity.exception.BaseException;
import com.marketing.activity.exception.ReceiveVoucherException;
import com.marketing.activity.handler.*;
import com.marketing.activity.helper.VoucherHelper;
import com.marketing.activity.mapper.mysql.marketingActivity.VoucherActivityInfoMapper;
import com.marketing.activity.mapper.mysql.marketingActivity.VoucherUserMapper;
import com.marketing.activity.service.c.AppVoucherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  app?????????????????????
 * </p>
 *
 * @author yyz
 * @since 2022/4/14
 */
@Service
@Slf4j
public class AppVoucherServiceImpl implements AppVoucherService {

    @Resource
    private VoucherActivityInfoMapper voucherActivityInfoMapper;
    @Resource
    private ExamDirectoryHandler examDirectoryHandler;
    @Resource
    private VoucherUserHandler voucherUserHandler;
    @Resource
    private VoucherHelper voucherHelper;
    @Resource
    private VoucherHandler voucherHandler;
    @Resource
    private UserHandler userHandler;
    @Resource
    private VoucherDataBuildHandler voucherDataBuildHandler;
    @Resource
    private VoucherUserMapper voucherUserMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public CommonResult<List<ExamGroupResp>> getExamGroup() {

        List<Integer> distinctColumn = voucherActivityInfoMapper.queryDistinctColumn();

        List<ExamDirectoryInfoDTO> examDirectoryList = examDirectoryHandler.getExamDirectoryListByIds(distinctColumn);
        if(CollUtil.isEmpty(examDirectoryList)){
            return CommonResult.success(Lists.newArrayList());
        }

        List<ExamGroupResp> respList = examDirectoryList.stream().map(info ->{
            ExamGroupResp respInfo = new ExamGroupResp();
            respInfo.setId(info.getId());
            respInfo.setName(info.getValue());
            return respInfo;
        }).collect(Collectors.toList());

        return CommonResult.success(respList);
    }

    @Override
    public CommonResult<CommonPage<ExamGroupPickVoucherResp>> getExamGroupPickList(ExamGroupPickVoucherParam queryParam) {
        Assert.notNull(queryParam, ErrorMsg.PARAM_IS_NULL);

        CommonPage<ExamGroupPickVoucherResp> commonPage = new CommonPage<>();

        //a
        Page<VoucherActivityInfo> page = new Page<>(queryParam.getCurrentPage(), queryParam.getPageSize());
        List<VoucherActivityInfo> activityList = voucherActivityInfoMapper.queryListByColumnId(page,queryParam.getExamGroupId());
        if(CollUtil.isEmpty(activityList)){
            return CommonResult.success(commonPage);
        }
        //?????????
        Map<Long/* voucherId */,List<VoucherUser>> voucherUserListMap = voucherUserHandler.getUserAllVoucherMap(queryParam.getUserId());

        List<ExamGroupPickVoucherResp> resultList = Lists.newArrayList();
        for(VoucherActivityInfo activityInfo: activityList){
            //V
            VoucherInfo voucherInfo = voucherHelper.getVoucherInfoByActivityId(activityInfo.getId());
            if(voucherInfo == null){
                continue;
            }

            ExamGroupPickVoucherResp respInfo = new ExamGroupPickVoucherResp();
            respInfo.setVoucherId(voucherInfo.getId().toString());
            respInfo.setVoucherCode(voucherInfo.getInnerCode());
            respInfo.setShowName(voucherInfo.getShowName());
            //????????????
            VoucherText voucherText = voucherHandler.getVoucherText(voucherInfo);
            respInfo.setUseRuleDesc(voucherText.getFullAmountsAvailableText());
            respInfo.setUseRangeDesc(voucherText.getUseRangeText());
            respInfo.setDiscountAmount(voucherText.getDiscountAmountText());
            //??????????????????--?????????
            respInfo.setUseTimeDesc(voucherHandler.getUseTimeText(voucherInfo.getUseTimeType(),
                    voucherInfo.getUseTimeEnd(),voucherInfo.getUseTimePlusDay()));
            //?????????
            boolean hasStatus = voucherUserListMap.containsKey(voucherInfo.getId());
            respInfo.setHas(hasStatus);
            respInfo.setHasNum(hasStatus ? voucherUserListMap.get(voucherInfo.getId()).size() : 0);
            //??????????????? 0????????????1????????????-1?????????
            int receiveStatus = 0;
            if(voucherInfo.getTotalNum() <= 0){
                receiveStatus = -1;
            }else{
                //????????????[???] -1 ??????????????? 0 ???????????? ??????0 ??????
                Integer eachLimit = voucherInfo.getEachLimit();
                if(eachLimit > 0 && hasStatus){
                    int size = voucherUserListMap.get(voucherInfo.getId()).size();
                    receiveStatus = eachLimit > size ? 0 : 1;
                }
            }
            respInfo.setReceiveStatus(receiveStatus);

            resultList.add(respInfo);
        }

        commonPage.setList(resultList);
        commonPage.setPageNum((int)page.getCurrent());
        commonPage.setPageSize((int)page.getSize());
        commonPage.setTotalPage((int)page.getPages());
        commonPage.setTotal(page.getTotal());

        return CommonResult.success(commonPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult<Boolean> receiveVoucher(ReceiveVoucherParam receiveVoucherParam) {
        long startTime = System.currentTimeMillis();

        Assert.notNull(receiveVoucherParam, ErrorMsg.PARAM_IS_NULL);

        String voucherCode = receiveVoucherParam.getVoucherCode();
        Assert.isFalse(StringUtils.isBlank(voucherCode), ErrorMsg.VOUCHER_CODE_IS_NULL);
        Long userId = receiveVoucherParam.getUserId();
        Assert.notNull(userId, ErrorMsg.USER_ID_IS_NULL);
        UserInfoDTO userInfoDTO = userHandler.getUserInfo(userId.intValue());
        Assert.notNull(userInfoDTO, ErrorMsg.USER_IS_NOT_EXIST);

        // redis??? 0.8 ???
        RedisLock redisLock = new RedisLock(redisTemplate, RedisKeyConstant.KEY_RECEIVE_VOUCHER+voucherCode,
                TimeUnit.MILLISECONDS, 800);
        boolean locked = redisLock.tryLock();
        log.info("receiveVoucher ??????redis??? locked[{}]", locked);
        Assert.isTrue(locked,"??????????????????????????????");

        try{
            VoucherDataResult voucherDataResult = voucherDataBuildHandler.getVoucherData(voucherCode);
            //????????????
            String receiveVoucherCheckData = voucherDataResult.receiveVoucherCheckData(Boolean.TRUE);
            Assert.isNull(receiveVoucherCheckData,receiveVoucherCheckData);
            //???
            VoucherInfo voucherInfo = voucherDataResult.getVoucherInfo();
            //??????
            Integer eachLimit = voucherInfo.getEachLimit();
            if(eachLimit > 0){
                //?????????
                List<VoucherUser> voucherUserList = voucherUserHandler.getUserVoucherList(userId,voucherInfo.getId());
                Assert.isFalse((CollUtil.isNotEmpty(voucherUserList) && voucherUserList.size() >= eachLimit),
                        "???????????????????????????"+eachLimit+"???");
            }
            VoucherUser insertInfo = new VoucherUser();
            insertInfo.setVoucherId(voucherInfo.getId());
            insertInfo.setVoucherCode(voucherInfo.getInnerCode());
            insertInfo.setUserId(receiveVoucherParam.getUserId());
            //????????????
            Date expireTime = voucherHandler.getExpireTime(voucherInfo);
            insertInfo.setExpireTime(expireTime);
            //???????????????
            voucherUserMapper.insert(insertInfo);

            //?????????
            int updateResult = voucherHelper.updateStock(voucherInfo.getId(), voucherInfo.getTotalNum() - 1);
            if(updateResult < 1){
                throw new ReceiveVoucherException("??????????????????");
            }
        }catch (Exception e){
            throw new BaseException(e.getMessage());
        }finally {
            redisLock.unlock();
            log.info("receiveVoucher end {}ms", DateUtil.spendMs(System.currentTimeMillis() - startTime));
        }


        return CommonResult.success(Boolean.TRUE);
    }
}
