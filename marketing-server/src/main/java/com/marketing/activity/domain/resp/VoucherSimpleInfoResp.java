package com.marketing.activity.domain.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 *  编辑优惠券返回参数
 * </p>
 *
 * @author yyz
 * @since 2022/3/29
 */

@ApiModel("编辑优惠券返回参数")
@Data
@Builder
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class VoucherSimpleInfoResp implements Serializable {

    @ApiModelProperty(value = "券ID", required = true)
    private Long voucherId;

    @ApiModelProperty(value = "券码", required = true)
    private String innerCode;

    @ApiModelProperty(value = "展示名称（用户侧）", required = true)
    private String showName;

    @ApiModelProperty(value = "活动ID", required = true)
    private String activityId;
}
