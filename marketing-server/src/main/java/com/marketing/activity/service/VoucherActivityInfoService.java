package com.marketing.activity.service;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marketing.activity.domain.param.VoucherActivityParam;

/**
 * <p>
 * 券活动 服务类
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
public interface VoucherActivityInfoService extends IService<VoucherActivityInfo> {

    CommonResult<Boolean> add(VoucherActivityParam voucherActivityParam);

    CommonResult<Boolean> edit(Long id, VoucherActivityParam voucherActivityParam);

    CommonResult<Boolean> state(Long id);
}
