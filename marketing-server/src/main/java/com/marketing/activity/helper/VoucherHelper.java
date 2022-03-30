package com.marketing.activity.helper;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.marketing.activity.constant.VoucherConstant;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.mapper.VoucherInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 券工具
 * @author yyz
 */
@Component
public class VoucherHelper {

    @Resource
    private VoucherInfoMapper voucherInfoMapper;

    public VoucherInfo convertToPo(VoucherInfoParam paramInfo){
        VoucherInfo resultInfo = new VoucherInfo();

        //基础属性
        resultInfo.setOuterName(paramInfo.getOuterName());
        resultInfo.setInnerName(paramInfo.getInnerName());
        resultInfo.setStock(paramInfo.getStock());
        resultInfo.setEachLimit(paramInfo.getEachLimit());

        //适用商品
        Integer useRangeType = paramInfo.getUseRangeType();
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
        resultInfo.setUseTimeType(paramInfo.getUseTimeType());
        if(useRangeType == 0){
            resultInfo.setUseTimeStart(new Date());
        }
        resultInfo.setUseTimeEnd(paramInfo.getUseTimeEnd());
        resultInfo.setUseTimePlusDay(paramInfo.getUseTimePlusDay());

        resultInfo.setOperator(paramInfo.getOperator());
        return resultInfo;
    }

    /**
     * 生成券码
     * @param voucherInfo
     * @return
     */
    public String generateCode(VoucherInfo voucherInfo) {
        Integer discountType = voucherInfo.getDiscountType();
        String prefix = this.getPrefix(discountType);

        Long id = voucherInfo.getId();

        BigDecimal fullAmounts = voucherInfo.getFullAmounts() == null ? BigDecimal.ZERO: voucherInfo.getFullAmounts();
        String full = fullAmounts.stripTrailingZeros().toPlainString();
        String m = full.length() > 4 ? full.substring(0,4) : full;

        String n = "0";
        BigDecimal discountAmounts = voucherInfo.getDiscountAmounts();
        if(discountType == 0){
            String discount = discountAmounts.stripTrailingZeros().toPlainString();
            n = discount.length() > 4 ? discount.substring(0,4) : discount;
        }else if(discountType == 1){
            String discount = discountAmounts.stripTrailingZeros().toPlainString();
            String[] split = discount.split("\\.");
            if(split.length == 1){
                n = split[0];
            }else{
                n = "0".equals(split[0]) ? split[1] : split[0];
            }
        }

        //可用时间分类 0 end, 1 x天可用， 2 start->end
        String d = "";
        Integer useTimeType = voucherInfo.getUseTimeType();
        if(useTimeType == 0){
            d = DateUtil.format(voucherInfo.getUseTimeEnd(), DatePattern.PURE_DATE_FORMAT);
        }else if(useTimeType == 1 || useTimeType == 2){
            d = voucherInfo.getUseTimePlusDay()+"d";
        }

        String code = String.format(VoucherConstant.CRM_CREATE_VOUCHER, prefix, id, m, n, d);

        VoucherInfo updateInfo = new VoucherInfo();
        updateInfo.setId(id);
        updateInfo.setInnerCode(code);

        voucherInfoMapper.updateById(updateInfo);

        return code;
    }

    private String getPrefix(Integer discountType) {
        String prefix = "";
        //优惠方式 0减免 1折扣
        switch(discountType){
            case 0:
                prefix = "J";
                break;
            case 1:
                prefix = "Z";
                break;
        }
        return prefix;
    }
}
