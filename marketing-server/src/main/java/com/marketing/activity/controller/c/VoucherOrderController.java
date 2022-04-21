package com.marketing.activity.controller.c;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.OrderConfirmCheckVoucherParam;
import com.marketing.activity.domain.resp.OrderConfirmCheckVoucherResp;
import com.marketing.activity.service.c.VoucherOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/12
 */
@ApiSupport(author = "yyz",order = 102)
@Api(tags = "订单优惠券")
@RestController
@RequestMapping("/c/voucher-order")
public class VoucherOrderController {

    private final VoucherOrderService voucherOrderService;

    public VoucherOrderController(VoucherOrderService voucherOrderService){
        this.voucherOrderService = voucherOrderService;
    }

    @ApiOperation("订单二次校验")
    @PostMapping("/confirm")
    public CommonResult<OrderConfirmCheckVoucherResp> confirm(@RequestBody OrderConfirmCheckVoucherParam orderConfirmCheckVoucherParam){
        return voucherOrderService.confirm(orderConfirmCheckVoucherParam);
    }
}
