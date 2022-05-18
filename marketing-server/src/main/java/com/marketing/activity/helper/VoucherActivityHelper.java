package com.marketing.activity.helper;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.param.VoucherActivityPageParam;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.mapper.mysql.marketingActivity.VoucherActivityInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    public List<VoucherActivityInfo> getPageList(VoucherActivityPageParam pageParam){

        LambdaQueryWrapper<VoucherActivityInfo> queryWrapper = Wrappers.lambdaQuery(VoucherActivityInfo.class);
        queryWrapper.eq(VoucherActivityInfo::getDeleteStatus, EnabledStatusEnum.YES.getValue());
        queryWrapper.like(StringUtils.isNotBlank(pageParam.getTitle()),VoucherActivityInfo::getTitle,pageParam.getTitle());
        queryWrapper.apply(StringUtils.isNotBlank(pageParam.getDepId()),"FIND_IN_SET('"+pageParam.getDepId()+",dep_id");
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
        return voucherActivityInfoMapper.selectList(queryWrapper);
    }
}
