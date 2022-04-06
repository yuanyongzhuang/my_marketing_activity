package com.marketing.activity.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.constant.ErrorMsg;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.entity.VoucherActivityRelation;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.domain.param.VoucherInfoPageParam;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.domain.resp.VoucherInfoResp;
import com.marketing.activity.domain.resp.VoucherSimpleInfoResp;
import com.marketing.activity.enums.EnabledStatusEnum;
import com.marketing.activity.helper.VoucherHelper;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import com.marketing.activity.mapper.VoucherActivityRelationMapper;
import com.marketing.activity.mapper.VoucherInfoMapper;
import com.marketing.activity.service.VoucherInfoService;
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

    private final VoucherHelper voucherHelper;

    public VoucherInfoServiceImpl(VoucherHelper voucherHelper){
        this.voucherHelper = voucherHelper;
    }

    @Resource
    private VoucherActivityRelationMapper voucherActivityRelationMapper;

    @Override
    public CommonResult<VoucherSimpleInfoResp> add(VoucherInfoParam voucherInfoParam) {
        //参数校验
        Assert.notNull(voucherInfoParam, ErrorMsg.PARAM_IS_NULL);
        String checkParamsResult = voucherInfoParam.checkParams();
        Assert.isNull(checkParamsResult, checkParamsResult);

        VoucherInfo voucherInfo = voucherHelper.convertToPo(voucherInfoParam);

        this.save(voucherInfo);

        //生成券码
        String code = voucherHelper.generateCode(voucherInfo);

        //券和活动关联
        VoucherActivityRelation relationInfo = new VoucherActivityRelation();
        relationInfo.setActivityId(voucherInfoParam.getActivityId());
        relationInfo.setVoucherId(voucherInfo.getId());
        relationInfo.setVoucherCode(code);
        voucherActivityRelationMapper.insert(relationInfo);


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
        //A
//        VoucherActivityInfo activityInfo = voucherActivityInfoMapper.selectById(voucherInfoParam.getActivityId());
//        Assert.isFalse((activityInfo == null || activityInfo.getDeleteStatus() == 1),"活动" + ErrorMsg.DOES_NOT_EXIST);
        //V
        VoucherInfo info = this.getById(id);
        Assert.isFalse((info == null || info.getDeleteStatus() == 1),"券" + ErrorMsg.DOES_NOT_EXIST);
        //R
//        LambdaQueryWrapper<VoucherActivityRelation> voucherActivityRelationLambdaQueryWrapper = Wrappers.lambdaQuery(VoucherActivityRelation.class);
//        voucherActivityRelationLambdaQueryWrapper.eq(VoucherActivityRelation::getActivityId, id);
//        VoucherActivityRelation relationInfo = voucherActivityRelationMapper.selectOne(voucherActivityRelationLambdaQueryWrapper);
//        Assert.notNull(relationInfo,"活动关联" + ErrorMsg.DOES_NOT_EXIST);

        String checkParamsResult = voucherInfoParam.checkParams();
        Assert.isFalse(StringUtils.isNotBlank(checkParamsResult), checkParamsResult);


        VoucherInfo voucherInfo = voucherHelper.convertToPo(voucherInfoParam);
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
}
