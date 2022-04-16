package com.marketing.activity.service.b.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherActivityPageParam;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.domain.resp.VoucherActivityInfoResp;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.handler.VoucherActivityHandler;
import com.marketing.activity.helper.VoucherActivityHelper;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import com.marketing.activity.service.b.VoucherActivityInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private VoucherActivityHelper voucherActivityHelper;

    @Resource
    private VoucherActivityHandler voucherActivityHandler;

    @Override
    public CommonResult<Boolean> add(VoucherActivityParam voucherActivityParam) {
        Assert.isFalse(voucherActivityParam == null, ErrorMsg.PARAM_IS_NULL);
        String checkParamsResult = voucherActivityParam.checkParams();
        Assert.isNull(checkParamsResult,checkParamsResult);
        VoucherActivityInfo voucherActivityInfo = voucherActivityHandler.convertToPo(voucherActivityParam);
        this.save(voucherActivityInfo);
        return CommonResult.success(Boolean.TRUE);
    }

    @Override
    public CommonResult<Boolean> edit(Long id, VoucherActivityParam voucherActivityParam) {
        Assert.notNull(voucherActivityParam, ErrorMsg.PARAM_IS_NULL);
        Assert.notNull(id,ErrorMsg.ID_IS_ERROR);
        VoucherActivityInfo info = this.getById(id);
        Assert.isFalse((info == null || info.getDeleteStatus() == 1),"活动" + ErrorMsg.DOES_NOT_EXIST);

        VoucherActivityInfo updateInfo = voucherActivityHandler.convertToPo(voucherActivityParam);
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
        Page<VoucherInfo> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        List<VoucherActivityInfoResp> respList = new ArrayList<>();
        List<VoucherActivityInfo> list = voucherActivityHelper.getPageList(pageParam);
        if(CollectionUtil.isNotEmpty(list)){
            respList = BeanUtil.copyToList(list, VoucherActivityInfoResp.class);
        }
        commonPage.setList(respList);
        commonPage.setPageNum((int)page.getCurrent());
        commonPage.setPageSize((int)page.getSize());
        commonPage.setTotalPage((int)page.getPages());
        commonPage.setTotal(page.getTotal());

        return commonPage;
    }

    @Override
    public CommonResult<VoucherActivityInfoResp> get(Long id) {
        VoucherActivityInfo info = this.getById(id);
        Assert.notNull(info,ErrorMsg.DOES_NOT_EXIST);
        VoucherActivityInfoResp resp = new VoucherActivityInfoResp();
        BeanUtil.copyProperties(info,resp,false);
        return CommonResult.success(resp);
    }

}
