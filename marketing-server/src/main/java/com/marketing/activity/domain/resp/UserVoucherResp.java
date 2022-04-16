package com.marketing.activity.domain.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yyz
 */
@ApiModel("用户优惠券列表返回值")
@Data
public class UserVoucherResp implements Serializable {

    @ApiModelProperty(value = "用户券ID", required = true, example = "123")
    @JsonProperty("voucherId")
    private String userVoucherId;

    @ApiModelProperty(value = "券码", required = true, example = "crm-J-9-0-10-20220430")
    private String voucherCode;

    @ApiModelProperty(value = "优惠券名称", required = true, example = "天降红包")
    private String showName;

    @ApiModelProperty(value = "可用时间描述", required = true, example = "截止时间 2022/04/01")
    private String useTimeDesc;

    @ApiModelProperty(value = "使用门槛（满额）描述", required = true, example = "满500元可用")
    private String useRuleDesc;

    @ApiModelProperty(value = "可用范围描述", required = true, example = "指定商品可用")
    private String useRangeDesc;

    @ApiModelProperty(value = "优惠金额/折扣", required = true, example = "100.00/7.0")
    private String discountAmount;

    // , required = true, example = "[原因1, 原因2]"
    @ApiModelProperty(value = "不可用原因")
    private List<String> unUseReasons;

}
