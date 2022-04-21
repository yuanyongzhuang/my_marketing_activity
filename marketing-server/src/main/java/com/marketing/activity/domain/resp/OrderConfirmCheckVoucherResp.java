package com.marketing.activity.domain.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/21
 */
@ApiModel("订单二次校验券信息返回参数")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderConfirmCheckVoucherResp implements Serializable {

    @ApiModelProperty(value = "用户券Id", required = true)
    private String userVoucherId;

    @ApiModelProperty(value = "券Id", required = true)
    private String voucherId;

    @ApiModelProperty(value = "券码", required = true)
    private String voucherCode;

    @ApiModelProperty(value = "优惠券名称", required = true)
    private String voucherName;

    @ApiModelProperty(value = "满额（门槛价）", required = true)
    private BigDecimal fullAmounts;

    @ApiModelProperty(value = "优惠方式 0减免 1折扣")
    private Integer discountType;

    @ApiModelProperty(value = "减额（100）/折扣（0.8）")
    private BigDecimal discountAmounts;

    @ApiModelProperty(value = "可用", required = true)
    private Boolean unavailable;

    @ApiModelProperty(value = "不可用原因", required = true)
    private String unavailableReason;

}
