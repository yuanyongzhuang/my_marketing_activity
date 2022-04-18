package com.marketing.activity.domain.resp;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yyz
 */
@ApiModel("用户优惠券列表-订单确认页结果集")
@Data
public class OrderConfirmVoucherResp implements Serializable {

    @ApiModelProperty(value = "可用券")
    private List<UserVoucherResp> available = Lists.newArrayList();

    @ApiModelProperty(value = "不可用券")
    private List<UserVoucherResp> unavailable = Lists.newArrayList();
}
