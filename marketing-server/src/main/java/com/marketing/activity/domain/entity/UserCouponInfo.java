package com.marketing.activity.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_coupon_info")
public class UserCouponInfo {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 券id
     */
    @TableField("coupon_id")
    private Long couponId;

    /**
     * 券码
     */
    @TableField("coupon_code")
    private String couponCode;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 使用状态 0 未使用 1 已使用 2 已转赠 3 已过期
     */
    @TableField("use_status")
    private Integer useStatus;

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
