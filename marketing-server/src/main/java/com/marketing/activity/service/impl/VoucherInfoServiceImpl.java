package com.marketing.activity.service.impl;

import cn.hutool.core.lang.Assert;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.domain.resp.VoucherSimpleInfoResp;
import com.marketing.activity.helper.VoucherHelper;
import com.marketing.activity.mapper.VoucherInfoMapper;
import com.marketing.activity.service.VoucherInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 券 服务实现类
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@Service
public class VoucherInfoServiceImpl extends ServiceImpl<VoucherInfoMapper, VoucherInfo> implements VoucherInfoService {

    private final VoucherHelper voucherHelper;

    public VoucherInfoServiceImpl(VoucherHelper voucherHelper){
        this.voucherHelper = voucherHelper;
    }

    @Override
    public CommonResult<VoucherSimpleInfoResp> add(VoucherInfoParam voucherInfoParam) {
        //参数校验
        Assert.notNull(voucherInfoParam, ErrorMsg.PARAM_IS_NULL);
        String checkParamsResult = voucherInfoParam.checkParams();
        Assert.isFalse(StringUtils.isNotBlank(checkParamsResult), checkParamsResult);

        VoucherInfo voucherInfo = voucherHelper.convertToPo(voucherInfoParam);

        this.save(voucherInfo);

        //生成券码
        String code = voucherHelper.generateCode(voucherInfo);

        VoucherSimpleInfoResp simpleInfoResp = VoucherSimpleInfoResp.builder()
                .voucherId(voucherInfo.getId())
                .innerCode(code)
                .innerName(voucherInfo.getInnerName())
                .outerName(voucherInfo.getOuterName())
                .build();

        return CommonResult.success(simpleInfoResp);
    }

    @Override
    public CommonResult<Boolean> edit(Long id, VoucherInfoParam voucherInfoParam) {
        Assert.notNull(voucherInfoParam, ErrorMsg.PARAM_IS_NULL);
        Assert.notNull(id,ErrorMsg.ID_IS_ERROR);
        String checkParamsResult = voucherInfoParam.checkParams();
        Assert.isFalse(StringUtils.isNotBlank(checkParamsResult), checkParamsResult);

        VoucherInfo voucherInfo = voucherHelper.convertToPo(voucherInfoParam);
        voucherInfo.setId(id);

        this.updateById(voucherInfo);

        return CommonResult.success(Boolean.TRUE);
    }
}
