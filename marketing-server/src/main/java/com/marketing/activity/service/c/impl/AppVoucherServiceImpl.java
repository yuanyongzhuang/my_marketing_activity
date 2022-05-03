package com.marketing.activity.service.c.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.dto.ExamDirectoryInfoDTO;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.domain.param.ExamGroupPickVoucherParam;
import com.marketing.activity.domain.resp.ExamGroupPickVoucherResp;
import com.marketing.activity.domain.resp.ExamGroupResp;
import com.marketing.activity.handler.ExamDirectoryHandler;
import com.marketing.activity.handler.VoucherUserHandler;
import com.marketing.activity.helper.VoucherHelper;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import com.marketing.activity.service.c.AppVoucherService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *
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
        //用户券
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
            //描述文案
            voucherHandler.getVoucherText(voucherInfo);


        }
        return null;
    }
}
