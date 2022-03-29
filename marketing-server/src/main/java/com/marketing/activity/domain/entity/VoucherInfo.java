package com.marketing.activity.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 券
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("voucher_info")
public class VoucherInfo {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 展示名称（用户侧）
     */
    @TableField("outer_name")
    private String outerName;

    /**
     * 内部编码
     */
    @TableField("inner_code")
    private String innerCode;

    /**
     * 内部名称
     */
    @TableField("inner_name")
    private String innerName;

    /**
     * 库存（可领取总量）
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 领取次数[人] -1 不可领取, 0 不限制, 大于0 限制
     */
    @TableField("each_limit")
    private Integer eachLimit;

    /**
     * 适用商品范围 0 全品类, 1 指定品类 2 指定商品
     */
    @TableField("use_range_type")
    private Integer useRangeType;

    /**
     * 满额（门槛价）
     */
    @TableField("full_amounts")
    private BigDecimal fullAmounts;

    /**
     * 优惠方式 0 减免, 1 折扣, 2 一口价
     */
    @TableField("discount_type")
    private Integer discountType;

    /**
     * 减额(100)/折扣(0.8)
     */
    @TableField("discount_amounts")
    private BigDecimal discountAmounts;

    /**
     * 减免封顶金额 0 上不封顶, 大于0 
     */
    @TableField("top_discount_amounts")
    private BigDecimal topDiscountAmounts;

    /**
     * 可用时间分类 0 end, 1 x天可用, 2 start->end
     */
    @TableField("use_time_type")
    private Integer useTimeType;

    /**
     * 可用起始时间
     */
    @TableField("use_time_start")
    private LocalDateTime useTimeStart;

    /**
     * 可用截止时间
     */
    @TableField("use_time_end")
    private LocalDateTime useTimeEnd;

    /**
     * 领取x天内可用
     */
    @TableField("use_time_plus_day")
    private Integer useTimePlusDay;

    /**
     * 扩展属性：指定品类/商品
     */
    @TableField("expand_json")
    private String expandJson;

    /**
     * 操作人
     */
    @TableField("operator")
    private String operator;

    /**
     * 启用状态 1 是 0 否
     */
    @TableField("enabled_status")
    private Integer enabledStatus;

    /**
     * 删除状态 1 是 0 否
     */
    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
