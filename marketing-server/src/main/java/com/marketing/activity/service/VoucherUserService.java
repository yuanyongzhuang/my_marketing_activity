package com.marketing.activity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.entity.VoucherUser;
import com.marketing.activity.domain.param.ChangeUseStatusParam;
import com.marketing.activity.domain.param.OrderConfirmVoucherParam;
import com.marketing.activity.domain.param.UserVoucherParam;
import com.marketing.activity.domain.resp.OrderConfirmVoucherResp;
import com.marketing.activity.domain.resp.UserVoucherResp;

/**
 * <p>
 * 用户优惠券 服务类
 * </p>
 *
 * @author yyz
 * @since 2022-04-12
 */
public interface VoucherUserService extends IService<VoucherUser> {

    /**
     * 用户优惠券列表
     * @param userVoucherParam 请求参数
     * @return obj
     */
    CommonResult<CommonPage<UserVoucherResp>> getMyVoucherList(UserVoucherParam userVoucherParam);

    /**
     * 用户优惠券列表-订单确认页
     * @param orderConfirmVoucherParam 请求参数
     * @return obj
     */
    CommonResult<OrderConfirmVoucherResp> getOrderConfirmVoucherList(OrderConfirmVoucherParam orderConfirmVoucherParam);

    /**
     * 修改用户优惠券状态
     * @param changeUseStatusParam 请求参数
     * @return obj
     */
    CommonResult<Boolean> changeUseStatus(ChangeUseStatusParam changeUseStatusParam);
}
