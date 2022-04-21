package com.marketing.activity.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/21
 */
@ApiModel("订单二次校验券信息请求参数")
@Data
public class OrderConfirmCheckVoucherParam implements Serializable {

    @ApiModelProperty(value = "用户Id", required = true, example = "123")
    private Long userId;

    @ApiModelProperty(value = "商品Id", required = true)
    private Set<Integer> productIds;

    @ApiModelProperty(value = "用户券Id", required = true)
    private Long userVoucherId;

    @ApiModelProperty(value = "当前时间", hidden = true)
    private Date currentTime = new Date();
}
