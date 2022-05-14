package com.marketing.activity.domain.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yyz
 */
@ApiModel("券领取记录")
@Data
@Builder
@AllArgsConstructor//有参构造
@NoArgsConstructor//无参构造
public class VoucherReceiveDataResp implements Serializable {


    @ApiModelProperty("用户券ID")
    private String userVoucherId;

    private String userId;

    @ApiModelProperty("领取手机号")
    private String mobile;

    @ApiModelProperty("领取手机号-脱敏")
    private String hideMobile;

    @ApiModelProperty("领取时间")
    private String receiveTime;

    @ApiModelProperty("使用时间")
    private String usedTime;

    @ApiModelProperty("到期时间")
    private String expireTime;

    @ApiModelProperty("使用状态 0 未使用 1 已使用 2 已过期")
    private Integer useStatus;

    @ApiModelProperty("使用状态")
    private String useStatusStr;

}
