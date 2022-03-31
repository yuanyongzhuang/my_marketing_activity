package com.marketing.activity.service.impl;

import cn.hutool.core.lang.Assert;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.helper.VoucherActivityHelper;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import com.marketing.activity.service.VoucherActivityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 券活动 服务实现类
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@Service
public class VoucherActivityInfoServiceImpl extends ServiceImpl<VoucherActivityInfoMapper, VoucherActivityInfo> implements VoucherActivityInfoService {

    private final VoucherActivityHelper voucherActivityHelper;

    public VoucherActivityInfoServiceImpl(VoucherActivityHelper voucherActivityHelper){
        this.voucherActivityHelper = voucherActivityHelper;
    }

    @Override
    public CommonResult<Boolean> add(VoucherActivityParam voucherActivityParam) {
        Assert.isFalse(voucherActivityParam == null, ErrorMsg.PARAM_IS_NULL);
        String checkParamsResult = voucherActivityParam.checkParams();
        Assert.isNull(checkParamsResult,checkParamsResult);
        VoucherActivityInfo voucherActivityInfo = voucherActivityHelper.convertToPo(voucherActivityParam);
        this.save(voucherActivityInfo);
        return CommonResult.success(Boolean.TRUE);
    }

    @Override
    public CommonResult<Boolean> edit(Long id, VoucherActivityParam voucherActivityParam) {
        Assert.notNull(voucherActivityParam, ErrorMsg.PARAM_IS_NULL);
        Assert.notNull(id,ErrorMsg.ID_IS_ERROR);
        VoucherActivityInfo info = this.getById(id);
        Assert.isFalse((info == null || info.getDeleteStatus() == 1),"活动" + ErrorMsg.DOES_NOT_EXIST);

        VoucherActivityInfo updateInfo = voucherActivityHelper.convertToPo(voucherActivityParam);
        updateInfo.setId(info.getId());

        this.updateById(updateInfo);

        return CommonResult.success(Boolean.TRUE);
    }
}
