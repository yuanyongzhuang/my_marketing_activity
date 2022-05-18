package com.marketing.activity.controller.c;


import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.ChangeUseStatusParam;
import com.marketing.activity.domain.param.OrderConfirmVoucherParam;
import com.marketing.activity.domain.param.UserVoucherParam;
import com.marketing.activity.domain.resp.OrderConfirmVoucherResp;
import com.marketing.activity.domain.resp.UserVoucherResp;
import com.marketing.activity.service.VoucherUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户优惠券 前端控制器
 * </p>
 *
 * @author yyz
 * @since 2022-04-12
 */
@ApiSupport(author = "yyz", order = 101)
@Api(tags = "用户优惠券")
@RestController
@RequestMapping("/c/voucher-user")
public class VoucherUserController {

    @Resource
    private VoucherUserService voucherUserService;

//    public VoucherUserController(VoucherUserService voucherUserService){
//        this.voucherUserService = voucherUserService;
//    }

    @ApiOperation("用户优惠券列表")
    @PostMapping("/getVoucherList")
    public CommonResult<CommonPage<UserVoucherResp>> getMyVoucherList(@RequestBody UserVoucherParam userVoucherParam){
        return voucherUserService.getMyVoucherList(userVoucherParam);
    }

    @ApiOperation("订单选券列表")
    @PostMapping("/getOrderConfirmVoucherList")
    public CommonResult<OrderConfirmVoucherResp> getOrderConfirmVoucherList(@RequestBody OrderConfirmVoucherParam orderConfirmVoucherParam){
        return voucherUserService.getOrderConfirmVoucherList(orderConfirmVoucherParam);
    }

    @ApiOperation("修改使用状态")
    @PostMapping("/changeUseStatus")
    public CommonResult<Boolean> changeUseStatus(@RequestBody ChangeUseStatusParam changeUseStatusParam){
        return voucherUserService.changeUseStatus(changeUseStatusParam);
    }
}
