package com.marketing.activity.helper;

import com.alibaba.fastjson.JSONObject;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.mapper.VoucherInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
        if(){
        }

    }

}
