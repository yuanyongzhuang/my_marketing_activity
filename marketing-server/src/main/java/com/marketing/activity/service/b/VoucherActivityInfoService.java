package com.marketing.activity.service.b;

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

    /**
     * 新增
     * @param voucherActivityParam 参数
     * @return obj
     */
    CommonResult<Boolean> add(VoucherActivityParam voucherActivityParam);

    /**
     * 更新
     * @param id id
     * @param voucherActivityParam 参数
     * @return obj
     */
    CommonResult<Boolean> edit(Long id, VoucherActivityParam voucherActivityParam);

    /**
     * 停止使用
     * @param id id
     * @return Boolean
     */
    CommonResult<Boolean> state(Long id);

    /**
     * 查询列表
     * @param pageParam 参数
     * @return obj
     */
    CommonPage<VoucherActivityInfoResp> getList(VoucherActivityPageParam pageParam);

    /**
     * 详情
     * @param id id
     * @return obj
     */
    CommonResult<VoucherActivityInfoResp> get(Long id);
}
