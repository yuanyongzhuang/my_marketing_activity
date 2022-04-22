package com.marketing.activity.service.c;

import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.ExamGroupPickVoucherParam;
import com.marketing.activity.domain.resp.ExamGroupPickVoucherResp;
import com.marketing.activity.domain.resp.ExamGroupResp;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/14
 */
public interface AppVoucherService {

    /**
     * 查询可领取优惠券的所属分类
     * @return list
     */
    CommonResult<List<ExamGroupResp>> getExamGroup();

    /**
     * 通过分类查询领券列表
     * @param examGroupPickVoucherParam param
     * @return obj
     */
    CommonResult<CommonPage<ExamGroupPickVoucherResp>> getExamGroupPickList(ExamGroupPickVoucherParam examGroupPickVoucherParam);
}
