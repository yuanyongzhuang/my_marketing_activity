package com.marketing.activity.handler;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.marketing.activity.BaseContextHandler;
import com.marketing.activity.domain.bo.VoucherText;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.helper.VoucherHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 券 处理器
 * </p>
 *
 * @author yyz
 * @since 2022/3/30
 */
@Component
public class VoucherHandler {

    @Resource
    VoucherHelper voucherHelper;

    public VoucherText getVoucherText(VoucherInfo voucherInfo) {
        VoucherText textResult = new VoucherText();

        //可用时间
        String userTimeText = this.getUseTimeText(voucherInfo.getUseTimeType(),voucherInfo.getUseTimeEnd(),voucherInfo.getUseTimePlusDay());
        textResult.setUseTimeText(userTimeText);
        //使用门槛
        String fullAmountAvailableText = this.getUseThresholdText(voucherInfo.getFullAmounts());
        textResult.setFullAmountsAvailableText(fullAmountAvailableText);
        //可用范围描述
        String useRangeText = this.getUseRangeText(voucherInfo.getUseRangeType());
        textResult.setUseRangeText(useRangeText);
        //优惠金额/折扣
        String discountAmountText = this.getUseDiscountAmountText(voucherInfo.getDiscountType(),voucherInfo.getDiscountAmounts());
        textResult.setDiscountAmountText(discountAmountText);

        return textResult;
    }

    /**
     * 优惠金额/折扣描述
     * @param discountType 类型
     * @param discountAmounts 金额/折扣
     * @return stirng
     */
    private String getUseDiscountAmountText(Integer discountType, BigDecimal discountAmounts) {
        String discountAmountText = "";
        if(discountType == 0){
            discountAmountText = "￥"+ discountAmounts.stripTrailingZeros().toPlainString();
        }else if(discountType == 1){
            discountAmountText = discountAmounts.multiply(BigDecimal.TEN).stripTrailingZeros().toPlainString()+"折";
        }
        return discountAmountText;
    }

    /**
     * 可用范围描述
     * @param useRangeType 范围类型
     * @return string
     */
    private String getUseRangeText(Integer useRangeType) {
        switch (useRangeType){
            case 0:
                return "全部商品可用";
            case 1:
                return "指定商品可用";
            case 2:
                return "部分商品可用";
            default:
                return "";
        }
    }

    /**
     * 使用门槛
     * @param fullAmounts 门槛金额
     * @return string
     */
    private String getUseThresholdText(BigDecimal fullAmounts) {
        String fullAmountAvailableText;
        BigDecimal threshold = fullAmounts == null ? BigDecimal.ZERO: fullAmounts;
        if(BigDecimal.ZERO.compareTo(threshold) <= 0){
            fullAmountAvailableText = "任意金额可用";
        }else{
            fullAmountAvailableText = "满" + threshold.stripTrailingZeros().toPlainString() + "元金额可用";
        }
        return fullAmountAvailableText;
    }

    /**
     * 可用时间
     * @param useTimeType 类型
     * @param useTimeEnd 时间
     * @param useTimePlusDay 天数
     * @return String
     */
    public String getUseTimeText(Integer useTimeType, Date useTimeEnd, Integer useTimePlusDay) {
        String useTime = "";
        switch (useTimeType){
            case 0:
                if(useTimeEnd != null){
                    useTime = "截止 " + DateUtil.formatDate(useTimeEnd);
                }
                break;
            case 1:
                useTime = "领取后 " + useTimePlusDay + " 天可用";
                break;
        }
        return useTime;
    }

    public VoucherInfo convertToPo(VoucherInfoParam paramInfo){
        VoucherInfo resultInfo = new VoucherInfo();

        //基础属性
        resultInfo.setShowName(paramInfo.getShowName());
        resultInfo.setStock(paramInfo.getStock());
        resultInfo.setTotalNum(paramInfo.getStock());
        resultInfo.setEachLimit(paramInfo.getEachLimit());

        //适用商品
        Integer useRangeType = paramInfo.getUseRangeType();
        resultInfo.setUseRangeType(useRangeType);
        if(useRangeType > 0){
            List<Integer> useRangeContent = paramInfo.getUseRangeContent();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("useRangeContent",useRangeContent);
            resultInfo.setExpandJson(jsonObject.toJSONString());
        }

        //减免、封顶
        resultInfo.setFullAmounts(paramInfo.getFullAmounts());
        resultInfo.setDiscountType(paramInfo.getDiscountType());
        resultInfo.setDiscountAmounts(paramInfo.getDiscountAmounts());
        resultInfo.setTopDiscountAmounts(paramInfo.getTopDiscountAmounts());

        //可用时段
        Integer useTimeType = paramInfo.getUseTimeType();
        resultInfo.setUseTimeType(useTimeType);
        if(useRangeType == 0){
            resultInfo.setUseTimeStart(BaseContextHandler.getAccessTime());
        }
        resultInfo.setUseTimeEnd(paramInfo.getUseTimeEnd());
        resultInfo.setUseTimePlusDay(paramInfo.getUseTimePlusDay());

        resultInfo.setOperator(paramInfo.getOperator());

        return resultInfo;
    }
}
