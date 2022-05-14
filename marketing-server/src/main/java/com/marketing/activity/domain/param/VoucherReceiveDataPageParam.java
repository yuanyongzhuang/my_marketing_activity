package com.marketing.activity.domain.param;

import com.marketing.activity.base.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yyz
 */
@ApiModel("领取记录上传参数")
@Data
public class VoucherReceiveDataPageParam extends BasePageQuery implements Serializable {

    @ApiModelProperty(value = "券id")
    private Long couponId;

    @ApiModelProperty(value= "领取手机号")
    private String mobile;

    @ApiModelProperty(value= "用户ID")
    private Long userId;

    @ApiModelProperty(value= "用户券ID")
    private String userVoucherId;

    @ApiModelProperty(value = "使用状态 0 未使用 1 已使用 2 已过期")
    private Integer couponStatus;

    @ApiModelProperty(value = "页数")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 10;
}
