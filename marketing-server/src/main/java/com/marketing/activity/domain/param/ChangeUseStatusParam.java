package com.marketing.activity.domain.param;

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
 * @since 2022/4/20
 */
@ApiModel("修改用户优惠券状态请求参数")
@Data
public class ChangeUseStatusParam implements Serializable {

    @ApiModelProperty("用户券Id")
    private Long userVoucherId;

    @ApiModelProperty("用户Id")
    private Long userId;
}
