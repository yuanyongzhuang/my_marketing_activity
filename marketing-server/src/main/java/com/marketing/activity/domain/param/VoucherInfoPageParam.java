package com.marketing.activity.domain.param;

import com.marketing.activity.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 券列表查询参数
 * @author yyz
 */
@Data
@ApiModel("活动列表查询参数")
public class VoucherInfoPageParam extends BasePageQuery {

    @ApiModelProperty(value = "活动id", required = true)
    @NotNull(message = "活动id不能为空")
    private Integer activityId;
}
