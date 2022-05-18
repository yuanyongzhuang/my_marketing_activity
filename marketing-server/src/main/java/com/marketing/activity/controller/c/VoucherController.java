package com.marketing.activity.controller.c;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.ExamGroupPickVoucherParam;
import com.marketing.activity.domain.param.ReceiveVoucherParam;
import com.marketing.activity.domain.resp.ExamGroupPickVoucherResp;
import com.marketing.activity.domain.resp.ExamGroupResp;
import com.marketing.activity.exception.ReceiveVoucherException;
import com.marketing.activity.service.c.AppVoucherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yyz
 * @since 2022/4/12
 */
@ApiSupport(author = "yyz",order = 100)
@Api(tags = "卡券-前端控制器")
@Slf4j
@RestController
@RequestMapping("/c/voucher")
public class VoucherController {

    @Resource
    private AppVoucherService appVoucherService;

//    public VoucherController(AppVoucherService appVoucherService){ this.appVoucherService = appVoucherService; }

    @ApiOperation(value = "优惠券分类")
    @GetMapping("/getExamGroup")
    public CommonResult<List<ExamGroupResp>> getExamGroup(){
        return appVoucherService.getExamGroup();
    }

    @ApiOperation("领券列表")
    @PostMapping("/getExamGroupPickList")
    public CommonResult<CommonPage<ExamGroupPickVoucherResp>> getExamGroupPickList(@RequestBody ExamGroupPickVoucherParam examGroupPickVoucherParam){
        return appVoucherService.getExamGroupPickList(examGroupPickVoucherParam);
    }

    @ApiOperation("领取优惠券")
    @PostMapping("/receiveVoucher")
    public CommonResult<Boolean> receiveVoucher(@RequestBody ReceiveVoucherParam receiveVoucherParam){
        try{
            return appVoucherService.receiveVoucher(receiveVoucherParam);
        }catch (Exception e){
            if(e instanceof ReceiveVoucherException){
                log.info("领取优惠券 业务异常={}",e.getMessage());
            }else{
                log.error("e=", e);
            }
            return CommonResult.failed("领取失败");
        }
    }

}
