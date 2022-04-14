package com.marketing.activity.service;

import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.param.VoucherActivityPageParam;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.domain.resp.VoucherActivityInfoResp;

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

    CommonPage<VoucherActivityInfoResp> getList(VoucherActivityPageParam pageParam);

    CommonResult<VoucherActivityInfoResp> get(Long id);
}
