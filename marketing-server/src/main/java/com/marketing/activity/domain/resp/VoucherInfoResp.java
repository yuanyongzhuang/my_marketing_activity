package com.marketing.activity.domain.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yyz
 */
@ApiModel("券详情")
@Data
@Builder
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class VoucherInfoResp {

    @ApiModelProperty(value = "券ID", required = true)
    private Long id;

    @ApiModelProperty(value = "展示名称（用户侧）", required = true)
    private String outerName;

    @ApiModelProperty(value = "内部编码", required = true)
    private String innerCode;

    @ApiModelProperty(value = "内部名称", required = true)
    private String innerName;

    @ApiModelProperty(value = "库存（可领取总量）", required = true)
    private Integer stock;

    @ApiModelProperty(value = "可领取数量（领取后要减数）", required = true)
    private Integer totalNum;

    @ApiModelProperty(value = "领取次数[人] -1 不可领取, 0 不限制, 大于0 限制", required = true)
    private Integer eachLimit;

    @ApiModelProperty(value = "适用商品范围 0 全品类, 1 指定品类 2 指定商品", required = true)
    private Integer useRangeType;

    @ApiModelProperty(value = "满额（门槛价）", required = true)
    private BigDecimal fullAmounts;

    @ApiModelProperty(value = "优惠方式 0 减免, 1 折扣, 2 一口价", required = true)
    private Integer discountType;

    @ApiModelProperty(value = "减额(100)/折扣(0.8)", required = true)
    private BigDecimal discountAmounts;

    @ApiModelProperty(value = "减免封顶金额 0 上不封顶, 大于0", required = true)
    private BigDecimal topDiscountAmounts;

    @ApiModelProperty(value = "可用时间分类 0 end, 1 x天可用, 2 start->end", required = true)
    private Integer useTimeType;

    @ApiModelProperty(value = "可用起始时间", required = true)
    private Date useTimeStart;

    @ApiModelProperty(value = "可用截止时间", required = true)
    private Date useTimeEnd;

    @ApiModelProperty(value = "领取x天内可用", required = true)
    private Integer useTimePlusDay;

    @ApiModelProperty(value = "扩展属性：指定品类/商品", required = true)
    private String expandJson;

    @ApiModelProperty(value = "操作人", required = true)
    private String operator;

    @ApiModelProperty(value = "启用状态 1 是 0 否", required = true)
    private Integer enabledStatus;

    @ApiModelProperty(value = "删除状态 1 是 0 否", required = true)
    private Integer deleteStatus;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "更新时间", required = true)
    private Date updateTime;
}
