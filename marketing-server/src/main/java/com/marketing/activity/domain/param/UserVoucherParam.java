package com.marketing.activity.domain.param;

import com.marketing.activity.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yyz
 */
@ApiModel("用户优惠券列表请求参数")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserVoucherParam extends BasePageQuery implements Serializable {

    @ApiModelProperty(value = "用户Id", required = true, example = "123")
    private Long userId;

    @ApiModelProperty(value = "优惠券状态：0未使用、1已使用、2已过期", required = true, example = "0")
    private Integer status;
}
