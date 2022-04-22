package com.marketing.activity.domain.param;

import com.marketing.activity.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/22
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel("领券列表请求参数")
@Data
public class ExamGroupPickVoucherParam extends BasePageQuery implements Serializable {

    @ApiModelProperty(value = "所属分类")
    private Integer examGroupId;

    @ApiModelProperty(value = "用户ID")
    private Long userId;
}

