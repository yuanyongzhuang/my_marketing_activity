package com.marketing.activity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 卡券活动关联
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("voucher_activity_relation")
public class VoucherActivityRelation {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动id
     */
    @TableField("activity_id")
    private Long activityId;

    /**
     * 券id
     */
    @TableField("voucher_id")
    private Long voucherId;

    /**
     * 券码
     */
    @TableField("voucher_code")
    private String voucherCode;


}
