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
 * 券活动
 * </p>
 *
 * @author yyz
 * @since 2022-04-12
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("voucher_activity_info")
public class VoucherActivityInfo {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    @TableField("title")
    private String title;

    /**
     * 所属组织
     */
    @TableField("dep_id")
    private String depId;

    /**
     * 所属分类（栏目）
     */
    @TableField("column_id")
    private Integer columnId;

    /**
     * 活动类型 1 券包, 2 兑换码, 3 通用口令
     */
    @TableField("activity_type")
    private Integer activityType;

    /**
     * 活动开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 扩展属性
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
