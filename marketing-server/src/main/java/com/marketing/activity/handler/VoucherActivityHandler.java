package com.marketing.activity.handler;


import com.alibaba.fastjson.JSONObject;
import com.marketing.activity.BaseContextHandler;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.helper.VoucherActivityHelper;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * 活动 处理器
 * @author yyz
 */
@Component
public class VoucherActivityHandler {

    @Resource
    VoucherActivityHelper voucherActivityHelper;

    public VoucherActivityInfo convertToPo(VoucherActivityParam param) {
        VoucherActivityInfo activityInfo = new VoucherActivityInfo();
        activityInfo.setTitle(param.getTitle());
        String depIds = param.getDepId().stream().map(String::valueOf).collect(Collectors.joining(","));
        activityInfo.setDepId(depIds);
        activityInfo.setActivityType(param.getActivityType());
        activityInfo.setColumnId(param.getColumnId());
        activityInfo.setStartTime(BaseContextHandler.getAccessTime());
        activityInfo.setEndTime(param.getEndTime());
        activityInfo.setOperator(param.getOperator());
        activityInfo.setEnabledStatus(1);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("remarks", param.getRemarks());
        activityInfo.setExpandJson(jsonObject.toJSONString());

        return activityInfo;
    }
}
