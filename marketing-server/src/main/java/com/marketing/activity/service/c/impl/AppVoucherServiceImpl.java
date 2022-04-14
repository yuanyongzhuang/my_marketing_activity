package com.marketing.activity.service.c.impl;

import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.resp.ExamGroupResp;
import com.marketing.activity.mapper.VoucherActivityInfoMapper;
import com.marketing.activity.service.c.AppVoucherService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/14
 */
@Service
public class AppVoucherServiceImpl implements AppVoucherService {

    @Resource
    private VoucherActivityInfoMapper voucherActivityInfoMapper;

    @Override
    public CommonResult<List<ExamGroupResp>> getExamGroup() {

        List<Integer> distinctColumn = voucherActivityInfoMapper.queryDistinctColumn();


        return null;
    }
}
