package com.marketing.activity.service.b;

import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherInfoPageParam;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.domain.param.VoucherReceiveDataPageParam;
import com.marketing.activity.domain.resp.VoucherInfoResp;
import com.marketing.activity.domain.resp.VoucherReceiveDataResp;
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

    /**
     *  新增
     * @param voucherInfoParam 参数
     * @return obj
     */
    CommonResult<VoucherSimpleInfoResp> add(VoucherInfoParam voucherInfoParam);

    /**
     * 更新
     * @param id id
     * @param voucherInfoParam 参数
     * @return obj
     */
    CommonResult<Boolean> edit(Long id, VoucherInfoParam voucherInfoParam);

    /**
     *  查询列表
     * @param pageParam 参数
     * @return obj
     */
    CommonPage<VoucherInfoResp> getList(VoucherInfoPageParam pageParam);

    /**
     * 停止使用
     * @param id id
     * @return Boolean
     */
    CommonResult<Boolean> state(Long id);

    /**
     * 详情
     * @param id id
     * @return obj
     */
    CommonResult<VoucherInfoResp> get(Long id);

    /**
     * 查询领取记录
     * @param pageParam 参数
     * @return obj
     */
    CommonPage<VoucherReceiveDataResp> voucherReceiveData(VoucherReceiveDataPageParam pageParam);
}
