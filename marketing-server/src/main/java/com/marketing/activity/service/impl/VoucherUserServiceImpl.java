package com.marketing.activity.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import com.marketing.activity.BaseContextHandler;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.bo.VoucherText;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.domain.param.UserVoucherParam;
import com.marketing.activity.domain.resp.UserVoucherResp;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.enums.UserVoucherStatusEnum;
import com.marketing.activity.handler.VoucherHandler;
import com.marketing.activity.helper.VoucherHelper;
import com.marketing.activity.helper.VoucherUserHelper;
import com.marketing.activity.mapper.VoucherUserMapper;
import com.marketing.activity.service.VoucherUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户优惠券 服务实现类
 * </p>
 *
 * @author yyz
 * @since 2022-04-12
 */
@Slf4j
@Service
public class VoucherUserServiceImpl extends ServiceImpl<VoucherUserMapper, VoucherUser> implements VoucherUserService {

    @Resource
    VoucherHandler voucherHandler;

    @Resource
    VoucherUserHelper voucherUserHelper;

    @Resource
    VoucherHelper voucherHelper;

    @Override
    public CommonResult<CommonPage<UserVoucherResp>> getMyVoucherList(UserVoucherParam userVoucherParam) {
        Assert.notNull(userVoucherParam, ErrorMsg.PARAM_IS_NULL);
        Long userId = userVoucherParam.getUserId();
        Assert.isFalse((userId == null || userId <= 0),ErrorMsg.USER_ID_IS_NULL);
        //状态 0未使用 1已使用 2已过期
        Integer status = userVoucherParam.getStatus();
        Assert.isFalse((status == null || status < 0),"券状态不能为空");

        CommonPage<UserVoucherResp> commonPage = new CommonPage<>();
        //用户券
        List<VoucherUser> voucherUserList = voucherUserHelper.getAllList(userId);
        if(CollectionUtil.isEmpty(voucherUserList)){
            return CommonResult.success(commonPage);
        }
        //过滤删除状态
        List<VoucherUser> filterDelList = voucherUserList.stream()
                .filter(info -> EnabledStatusEnum.NO.getValue().equals(info.getDeleteStatus()))
                .collect(Collectors.toList());

        //过滤状态
        List<VoucherUser> filterStatusList = Lists.newArrayList();
        switch (status){
            case 0:
                //未使用
                filterStatusList = filterDelList.stream()
                        .filter(info -> UserVoucherStatusEnum.UNUSED.getValue().equals(info.getUseStatus())
                                && info.getExpireTime().after(BaseContextHandler.getAccessTime()))
                        .collect(Collectors.toList());
                break;
            case 1:
                //已使用
                filterStatusList = filterDelList.stream()
                        .filter(info -> UserVoucherStatusEnum.USED.getValue().equals(info.getUseStatus()))
                        .collect(Collectors.toList());
                break;
            case 2:
                //已过期
                filterStatusList = filterDelList.stream()
                        .filter(info -> UserVoucherStatusEnum.UNUSED.getValue().equals(info.getUseStatus())
                                && info.getExpireTime().before(BaseContextHandler.getAccessTime()))
                        .collect(Collectors.toList());
                break;
        }
        if(CollectionUtil.isEmpty(filterStatusList)){
            return CommonResult.success(commonPage);
        }
        //记录总数
        commonPage.setTotal(filterStatusList.size());
        //创建时间倒序
        List<VoucherUser> sortedList = filterStatusList.stream()
                .sorted(Comparator.comparing(VoucherUser::getCreateTime).reversed()).collect(Collectors.toList());
        //处理分页数据
        List<List<VoucherUser>> partition = Lists.partition(sortedList, userVoucherParam.getPageSize());
        if(partition.size() < userVoucherParam.getCurrentPage()){
            return CommonResult.success(commonPage);
        }
        List<VoucherUser> subList = partition.get(userVoucherParam.getCurrentPage() - 1);

        //批量查询券信息
        Set<Long> voucherIds = subList.stream().map(VoucherUser::getVoucherId).collect(Collectors.toSet());
        List<VoucherInfo> voucherInfos = voucherHelper.batchQueryVoucherListByIds(voucherIds);
        Map<Long, VoucherInfo> voucherInfoMap = voucherInfos.stream().collect(
                Collectors.toMap(VoucherInfo::getId, Function.identity(), (v1, v2) -> v1));

        List<UserVoucherResp> respList = Lists.newArrayList();
        for(VoucherUser info: subList){
            Long id = info.getId();

            VoucherInfo voucherInfo = voucherInfoMap.get(info.getVoucherId());
            if(Objects.isNull(voucherIds)){
                log.info("getOrderConfirmVoucherList 用户券ID=[{}] voucher info is null", id);
                continue;
            }

            UserVoucherResp respInfo = new UserVoucherResp();
            //用户券ID
            respInfo.setUserVoucherId(id.toString());
            //券
            respInfo.setVoucherCode(voucherInfo.getInnerCode());
            respInfo.setShowName(voucherInfo.getShowName());
            //描述文案
            VoucherText voucherText = voucherHandler.getVoucherText(voucherInfo);
            respInfo.setUseRuleDesc(voucherText.getFullAmountsAvailableText());
            respInfo.setUseRangeDesc(voucherText.getUseRangeText());
            respInfo.setDiscountAmount(voucherText.getDiscountAmountText());
            //过期时间
            respInfo.setUseTimeDesc("截至 "+ DateUtil.formatDate(info.getExpireTime()));

            respList.add(respInfo);
        }

        commonPage.setList(respList);
        return CommonResult.success(commonPage);
    }
}
