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

import java.util.Date;

/**
 * 活动返回实体
 * @author yyz
 */
@ApiModel("活动详情")
@Data
@Builder
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class VoucherActivityInfoResp {

    @ApiModelProperty(value = "活动ID", required = true)
    private Long id;

    @ApiModelProperty(value = "活动名称", required = true)
    private String title;

    @ApiModelProperty(value = "所属组织", required = true)
    private String depId;

    @ApiModelProperty(value = "所属分类（栏目）", required = true)
    private Integer columnId;

    @ApiModelProperty(value = "活动类型 1 券包, 2 兑换码, 3 通用口令", required = true)
    private Integer activityType;

    @ApiModelProperty(value = "活动开始时间", required = true)
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间", required = true)
    private Date endTime;

    @ApiModelProperty(value = "扩展属性：x天可用、封顶金额、指定品类/商品", required = true)
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
