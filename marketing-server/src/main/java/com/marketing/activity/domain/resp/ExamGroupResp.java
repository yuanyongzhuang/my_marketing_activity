package com.marketing.activity.domain.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/14
 */
@ApiModel("查询可领卡券的所属分类返回值")
@Data
public class ExamGroupResp implements Serializable {

    @ApiModelProperty(value = "id", required = true)
    private Integer id;

    @ApiModelProperty(value = "名称", required = true)
    private String name;
}
