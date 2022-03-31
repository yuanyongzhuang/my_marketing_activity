package com.marketing.activity.domain.param;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 活动请求参数
 * @author yyz
 */
@ApiModel("活动请求参数")
@Data
public class VoucherActivityParam {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty(value = "活动名称", required = true)
    private String title;

    @ApiModelProperty(value = "所属组织", required = true)
    private List<Integer> depId;

    @ApiModelProperty(value = "活动类型 1 券包, 2 兑换码, 3 通用口令", required = true)
    private Integer activityType;

    @ApiModelProperty(value = "活动开始时间", required = true)
    private Date startTime;

    @ApiModelProperty(value = "活动结束时间", required = true)
    private Date endTime;

    @ApiModelProperty(value = "备注", required = true)
    private String remarks;

    @ApiModelProperty(value = "操作人", required = true)
    private String operator;

    public String checkParams() {

        if (this.title == null) {
            return "活动名称为空";
        }
        if (this.depId == null) {
            return "所属组织";
        }
        if (this.activityType == null) {
            return "活动类型为空";
        }
        if (this.startTime == null) {
            return"活动开始时间为空";
        }
        if (this.endTime == null) {
            return "活动结束时间为空";
        }
        if (this.remarks == null) {
            return "备注为空";
        }
        if (StringUtils.isBlank(this.operator)) {
            return "操作人为空";
        }

        return null;
    }

}
