package com.marketing.activity.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 券请求参数
 * </p>
 *
 * @author yyz
 * @since 2022/3/29
 */
@ApiModel("卡券请求参数")
@Data
public class VoucherInfoParam implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("activityId")
    private Long activityId;

    @ApiModelProperty(value = "券展示名称（用户侧）", required = true)
    private String outerName;

//    @ApiModelProperty(value = "券码", required = true)
//    private String innerCode;

    @ApiModelProperty(value = "内部名称", required = true)
    private String innerName;

    @ApiModelProperty(value = "库存（可领取总量）", required = true)
    private Integer stock;

    @ApiModelProperty(value = "领取次数[人] -1 不可领取, 0 不限制, 大于0 限制")
    private Integer eachLimit;

    @ApiModelProperty(value = "适用商品范围 0 全品类, 1 指定品类 2 指定商品", required = true)
    private Integer useRangeType;

    @ApiModelProperty(value = "满额（门槛价）")
    private BigDecimal fullAmounts;

    @ApiModelProperty(value = "优惠方式 0 减免, 1 折扣, 2 一口价", required = true)
    private Integer discountType;

    @ApiModelProperty(value = "减额(100)/折扣(0.8)", required = true)
    private BigDecimal discountAmounts;

    @ApiModelProperty(value = "优惠封顶金额 0 上不封顶, 大于0 ")
    private BigDecimal topDiscountAmounts;

    @ApiModelProperty(value = "可用时间分类 0 end, 1 x天可用, 2 start->end")
    private Integer useTimeType;

    @ApiModelProperty(value = "可用起始时间")
    private Date useTimeStart;

    @ApiModelProperty(value = "可用截止时间")
    private Date useTimeEnd;

    @ApiModelProperty(value = "领取x天内可用")
    private Integer useTimePlusDay;

    @ApiModelProperty(value = "操作人", required = true)
    private String operator;

    @ApiModelProperty(value = "适用商品范围[品类/商品]")
    private List<Integer> useRangeContent;

    public String checkParams() {

        if (this.activityId == null) {
            return "活动id为空";
        }
        if (this.discountType == null) {
            return "优惠方式为空";
        }
        if (this.discountType == 1 && BigDecimal.ZERO.compareTo(discountAmounts) == 0) {
            return "折扣力度为0";
        }
        if (this.useRangeType == null) {
            return "使用范围为空";
        }
        if (this.useRangeType > 0 && CollectionUtils.isEmpty(this.useRangeContent)) {
            return"适用商品[项目或商品]为空";
        }

        // 基础属性
        if (StringUtils.isBlank(this.outerName)) {
            return "券展示名称（用户侧）为空";
        }
//        if (StringUtils.isBlank(this.innerCode)) {
//            return "券码为空";
//        }
        if (StringUtils.isBlank(this.innerName)) {
            return "内部名称为空";
        }
        if (this.stock == null) {
            return "领取总量为空";
        }
        if (this.eachLimit == null) {
            return "每人领取次数为空";
        }

        // 可用时段 - 可用时间分类 0 start->end, 1 end, 2 x天可用
        if (this.useTimeType == null) {
            return "券可用时间类型为空";
        }
        switch (this.useTimeType) {
            case 0:
                if (useTimeEnd == null) {
                    return "券可用时间为空[截止时间]";
                }
                break;
            case 1:
                if (useTimePlusDay == null) {
                    return "券可用时间为空[领取x天内可用]";
                }
                break;
            case 2:
                if (useTimeStart == null || useTimeEnd == null) {
                    return "券可用时间为空[开始->结束]";
                }
                break;
        }


        if (StringUtils.isBlank(this.operator)) {
            return "操作人为空";
        }

        return null;
    }
}
