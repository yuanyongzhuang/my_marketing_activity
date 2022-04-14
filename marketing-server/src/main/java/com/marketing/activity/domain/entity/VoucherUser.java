package com.marketing.activity.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户优惠券
 * </p>
 *
 * @author yyz
 * @since 2022-04-12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("voucher_user")
public class VoucherUser {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 使用状态 0 未使用 1 已使用 2 已过期
     */
    @TableField("use_status")
    private Integer useStatus;

    /**
     * 使用时间
     */
    @TableField("use_time")
    private Date useTime;
    /**
     * 过期时间
     */
    @TableField("expire_time")
    private Date expireTime;

    /**
     * 删除状态 1 是 0 否
     */
    @TableField("delete_status")
    private Integer deleteStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
