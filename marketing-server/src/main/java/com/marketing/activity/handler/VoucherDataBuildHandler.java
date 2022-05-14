package com.marketing.activity.handler;

import com.marketing.activity.domain.bo.VoucherDataResult;
import com.marketing.activity.domain.entity.VoucherActivityInfo;
import com.marketing.activity.domain.entity.VoucherInfo;
import com.marketing.activity.helper.VoucherHelper;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import com.marketing.activity.mapper.VoucherInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 券数据构建
 * @author yyz
 */
@Component
public class VoucherDataBuildHandler {

    @Resource
    private VoucherActivityInfoMapper voucherActivityInfoMapper;

    @Resource
    private VoucherInfoMapper voucherInfoMapper;

    @Resource
    private VoucherHelper voucherHelper;


    public VoucherDataResult getVoucherData(String voucherCode) {
        VoucherInfo voucherInfo = voucherHelper.getVoucherInfoByCode(voucherCode);
        if(voucherInfo == null){
            return null;
        }
        VoucherActivityInfo activityInfo = voucherActivityInfoMapper.selectById(voucherInfo.getActivityId());
        VoucherDataResult voucherDataResult = new VoucherDataResult();
        voucherDataResult.setVoucherInfo(voucherInfo);
        voucherDataResult.setActivityInfo(activityInfo);
        return voucherDataResult;
    }
}
