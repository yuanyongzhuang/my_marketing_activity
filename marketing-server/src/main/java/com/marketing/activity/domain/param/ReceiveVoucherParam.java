package com.marketing.activity.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yyz
 */
@Data
@ApiModel("领取优惠券请求参数")
public class ReceiveVoucherParam implements Serializable {

    @ApiModelProperty(value = "券码", required = true, example = "crm-J-9-0-10-200220401")
    private String voucherCode;

    @ApiModelProperty(value = "用户Id", required = true, example = "1232")
    private Long userId;
}
