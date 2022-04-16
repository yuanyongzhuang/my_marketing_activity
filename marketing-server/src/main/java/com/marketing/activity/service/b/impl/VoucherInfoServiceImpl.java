package com.marketing.activity.service.b.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherInfoPageParam;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.domain.resp.VoucherInfoResp;
import com.marketing.activity.domain.resp.VoucherSimpleInfoResp;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.handler.VoucherHandler;
import com.marketing.activity.helper.VoucherHelper;
import com.marketing.activity.mapper.VoucherInfoMapper;
import com.marketing.activity.service.b.VoucherInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private VoucherHelper voucherHelper;
    @Resource
    private VoucherHandler voucherHandler;


    @Override
    public CommonResult<VoucherSimpleInfoResp> add(VoucherInfoParam voucherInfoParam) {
        //参数校验
        Assert.notNull(voucherInfoParam, ErrorMsg.PARAM_IS_NULL);
        String checkParamsResult = voucherInfoParam.checkParams();
        Assert.isNull(checkParamsResult, checkParamsResult);

        VoucherInfo voucherInfo = voucherHandler.convertToPo(voucherInfoParam);

        this.save(voucherInfo);

        //生成券码
        String code = voucherHelper.generateCode(voucherInfo);

        VoucherSimpleInfoResp simpleInfoResp = VoucherSimpleInfoResp.builder()
                .voucherId(voucherInfo.getId())
                .innerCode(code)
                .showName(voucherInfo.getShowName())
                .build();

        return CommonResult.success(simpleInfoResp);
    }

    @Override
    public CommonResult<Boolean> edit(Long id, VoucherInfoParam voucherInfoParam) {
        Assert.notNull(voucherInfoParam, ErrorMsg.PARAM_IS_NULL);
        Assert.notNull(id,ErrorMsg.ID_IS_ERROR);
        VoucherInfo info = this.getById(id);
        Assert.isFalse((info == null || info.getDeleteStatus() == 1),"券" + ErrorMsg.DOES_NOT_EXIST);

        String checkParamsResult = voucherInfoParam.checkParams();
        Assert.isFalse(StringUtils.isNotBlank(checkParamsResult), checkParamsResult);


        VoucherInfo voucherInfo = voucherHandler.convertToPo(voucherInfoParam);
        voucherInfo.setId(id);

        this.updateById(voucherInfo);

        return CommonResult.success(Boolean.TRUE);
    }

    @Override
    public CommonPage<VoucherInfoResp> getList(VoucherInfoPageParam pageParam) {

        CommonPage<VoucherInfoResp> commonPage = new CommonPage<>();
        List<VoucherInfoResp> respList = new ArrayList<>();

        Page<VoucherInfo> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        List<VoucherInfo> list = voucherHelper.getList(pageParam);
        if(CollectionUtil.isNotEmpty(list)){
            respList = BeanUtil.copyToList(list, VoucherInfoResp.class);
        }

        commonPage.setList(respList);
        commonPage.setPageNum((int)page.getCurrent());
        commonPage.setPageSize((int)page.getSize());
        commonPage.setTotalPage((int)page.getPages());
        commonPage.setTotal(page.getTotal());

        return commonPage;
    }

    @Override
    public CommonResult<Boolean> state(Long id) {
        Assert.notNull(id,ErrorMsg.ID_IS_NULL);

        VoucherInfo updateInfo = new VoucherInfo();
        updateInfo.setId(id);
        updateInfo.setEnabledStatus(EnabledStatusEnum.NO.getValue());

        this.updateById(updateInfo);

        return CommonResult.success(Boolean.TRUE);
    }

    @Override
    public CommonResult<VoucherInfoResp> get(Long id) {
        VoucherInfo info = this.getById(id);
        VoucherInfoResp resp = new VoucherInfoResp();
        BeanUtil.copyProperties(info, resp, false);
        return CommonResult.success(resp);
    }
}
