package com.marketing.activity.domain.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yyz
 */
@Data
public class VoucherText implements Serializable {

    /**
     * 可用时间.
     */
    private String useTimeText;

    /**
     * 使用门槛.
     */
    private String fullAmountsAvailableText;

    /**
     * 可用范围描述.
     */
    private String useRangeText;


    /**
     * 优惠金额/折扣.
     */
    private String discountAmountText;

}
