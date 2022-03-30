package com.marketing.activity.service;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.domain.resp.VoucherSimpleInfoResp;

/**
 * <p>
 * 券 服务类
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
public interface VoucherInfoService extends IService<VoucherInfo> {

    CommonResult<VoucherSimpleInfoResp> add(VoucherInfoParam voucherInfoParam);

    CommonResult<Boolean> edit(Long id, VoucherInfoParam voucherInfoParam);
}
