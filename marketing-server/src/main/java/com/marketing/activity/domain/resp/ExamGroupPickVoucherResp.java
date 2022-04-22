package com.marketing.activity.domain.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/22
 */
@ApiModel("通过分类查询领券列表返回值")
@Data
public class ExamGroupPickVoucherResp implements Serializable {

    @ApiModelProperty(value = "券ID", required = true, example = "123")
    private String voucherId;

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

    @ApiModelProperty(value = "优惠金额/折扣", required = true, example = "100元/7折")
    private String discountAmount;


    @ApiModelProperty(value = "用户领取状态", required = true, example = "false")
    private Boolean has = false;

    @ApiModelProperty(value = "已领取数量", required = true, example = "0")
    private Integer hasNum;

    @ApiModelProperty(value = "可领取状态 0 可领取，1 已领取，-1 已抢光", required = true, example = "0")
    private Integer receiveStatus;

}
