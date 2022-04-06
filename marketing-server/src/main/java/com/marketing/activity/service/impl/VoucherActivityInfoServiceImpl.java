package com.marketing.activity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.param.VoucherActivityPageParam;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.domain.resp.VoucherActivityInfoResp;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.helper.VoucherActivityHelper;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import com.marketing.activity.service.VoucherActivityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public CommonResult<Boolean> state(Long id) {
        Assert.notNull(id,ErrorMsg.ID_IS_NULL);

        VoucherActivityInfo updateInfo = new VoucherActivityInfo();
        updateInfo.setId(id);
        updateInfo.setEnabledStatus(EnabledStatusEnum.NO.getValue());
        this.updateById(updateInfo);

        return CommonResult.success(Boolean.TRUE);
    }

    @Override
    public CommonPage<VoucherActivityInfoResp> getList(VoucherActivityPageParam pageParam) {
        CommonPage<VoucherActivityInfoResp> commonPage = new CommonPage<>();
        List<VoucherActivityInfoResp> respList = new ArrayList<>();


        return commonPage;
    }
}
