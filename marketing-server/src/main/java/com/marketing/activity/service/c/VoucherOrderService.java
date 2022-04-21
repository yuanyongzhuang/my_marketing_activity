package com.marketing.activity.service.c;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.OrderConfirmCheckVoucherParam;
import com.marketing.activity.domain.resp.OrderConfirmCheckVoucherResp;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/21
 */
public interface VoucherOrderService {

    /**
     * 订单二次校验券信息
     * @param orderConfirmCheckVoucherParam 请求参数
     * @return obj
     */
    CommonResult<OrderConfirmCheckVoucherResp> confirm(OrderConfirmCheckVoucherParam orderConfirmCheckVoucherParam);
}
