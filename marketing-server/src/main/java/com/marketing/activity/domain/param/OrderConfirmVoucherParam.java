package com.marketing.activity.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author yyz
 */
@ApiModel("订单确认页面优惠券请求参数")
@Data
public class OrderConfirmVoucherParam implements Serializable {

    @ApiModelProperty(value = "用户id", required = true, example = "123")
    private Long userId;

    @ApiModelProperty(value = "商品id")
    private Set<Integer> productIds;
}
