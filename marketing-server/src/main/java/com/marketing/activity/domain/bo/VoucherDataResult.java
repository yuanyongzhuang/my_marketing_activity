package com.marketing.activity.domain.bo;

import com.marketing.activity.BaseContextHandler;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.enums.EnabledStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 券维度数据
 * @author yyz
 */
@Data
public class VoucherDataResult implements Serializable {

    /**
     * 券
     */
    private VoucherInfo voucherInfo;

    /**
     * 活动
     */
    private VoucherActivityInfo activityInfo;

    public String receiveVoucherCheckData(Boolean checkActivity){
        if(voucherInfo == null){
            return ErrorMsg.VOUCHER_NOT_EXIST;
        }
        //v
        //可领取数量（领取后要减数）
        Integer totalNum = voucherInfo.getTotalNum();
        if(totalNum <= 0){
            return "现场太火爆，券已抢光~";
        }
        //可用时间分类 0end 1x天可用 2start->end
        Integer useTimeType = voucherInfo.getUseTimeType();
        if(useTimeType == 0){
            //截止时间
            Date useTimeEnd = voucherInfo.getUseTimeEnd();
            if(useTimeEnd.before((BaseContextHandler.getAccessTime()))){
                return "优惠券已过期";
            }
        }
        //a
        if(Boolean.TRUE.equals(checkActivity)){
            if(activityInfo == null || EnabledStatusEnum.YES.getValue().equals(activityInfo.getDeleteStatus())){
                return ErrorMsg.ACTIVITY_NOT_EXIST;
            }
            if(EnabledStatusEnum.NO.getValue().equals(activityInfo.getEnabledStatus())){
                return "活动暂未投放";
            }
            Date startTime = activityInfo.getStartTime();
            if(startTime.after(BaseContextHandler.getAccessTime())){
                return "活动未开始";
            }
            Date endTime = activityInfo.getEndTime();
            if(endTime.before(BaseContextHandler.getAccessTime())){
                return "活动已结束";
            }
        }
        return null;
    }

}
