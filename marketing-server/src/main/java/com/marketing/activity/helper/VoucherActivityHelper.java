package com.marketing.activity.helper;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
}
