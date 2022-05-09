package com.marketing.activity.handler;

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


}
