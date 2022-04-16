package com.marketing.activity.domain.param;

import com.marketing.activity.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 活动列表查询参数
 * @author yyz
 */
@Data
@ApiModel("活动列表查询参数")
@EqualsAndHashCode(callSuper = true)
public class VoucherActivityPageParam extends BasePageQuery implements Serializable {

    @ApiModelProperty(value = "活动名称")
    private String title;

    @ApiModelProperty(value = "启用状态：1是 0否")
    private Integer enabledStatus;

    @ApiModelProperty(value = "事业部id")
    private String depId;

    @ApiModelProperty(value = "栏目id")
    private Integer columnId;


}



