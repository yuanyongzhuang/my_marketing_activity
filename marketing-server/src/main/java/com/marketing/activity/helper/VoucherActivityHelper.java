package com.marketing.activity.helper;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.marketing.activity.domain.param.VoucherActivityPageParam;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.enums.EnabledStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动 工具
 * </p>
 *
 * @author yyz
 * @since 2022/3/30
 */
@Component
public class VoucherActivityHelper {

    @Resource
    private VoucherActivityInfoMapper voucherActivityInfoMapper;

    public VoucherActivityInfo convertToPo(VoucherActivityParam param) {
        VoucherActivityInfo activityInfo = new VoucherActivityInfo();
        activityInfo.setTitle(param.getTitle());
        String depIds = param.getDepId().stream().map(String::valueOf).collect(Collectors.joining(","));
        activityInfo.setDepId(depIds);
        activityInfo.setActivityType(param.getActivityType());
        activityInfo.setStartTime(param.getStartTime());
        activityInfo.setEndTime(DateUtil.parseDateTime(param.getEndTime()+ " 23:59:59"));
        activityInfo.setOperator(param.getOperator());
        activityInfo.setEnabledStatus(1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("remarks", param.getRemarks());
        activityInfo.setExpandJson(jsonObject.toJSONString());

        return activityInfo;
    }

    public List<VoucherActivityInfo> getList(VoucherActivityPageParam pageParam){

        LambdaQueryWrapper<VoucherActivityInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(VoucherActivityInfo::getDeleteStatus, EnabledStatusEnum.YES.getValue());
        if(StringUtils.isNotBlank(pageParam.getTitle())){
            queryWrapper.like(VoucherActivityInfo::getTitle,pageParam.getTitle());
        }
        if(pageParam.getDepId() != null){
            queryWrapper.apply("FIND_IN_SET('"+pageParam.getDepId()+",dep_id");
        }
        Integer enabledStatus = pageParam.getEnabledStatus();
        if(enabledStatus != null){
            String formatDateTime = DateUtil.formatDateTime(new Date());
            if(EnabledStatusEnum.YES.getValue().equals(enabledStatus)){
                queryWrapper.eq(VoucherActivityInfo::getEnabledStatus,enabledStatus);
                queryWrapper.le(VoucherActivityInfo::getStartTime,formatDateTime)
                        .ge(VoucherActivityInfo::getEndTime,formatDateTime);
            }
            if(EnabledStatusEnum.NO.getValue().equals(enabledStatus)){
                queryWrapper.and(wrapper ->
                        wrapper.eq(VoucherActivityInfo::getEnabledStatus,EnabledStatusEnum.NO.getValue())
                                .or()
                                .le(VoucherActivityInfo::getEndTime,formatDateTime)
                );
            }
        }
        //更新时间倒序
        queryWrapper.orderByDesc(VoucherActivityInfo::getUpdateTime);
        List<VoucherActivityInfo> resultList = voucherActivityInfoMapper.selectList(queryWrapper);
        return resultList;
    }
}
