package com.marketing.activity.controller;


import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.service.VoucherActivityInfoService;
import com.marketing.activity.service.VoucherActivityRelationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 券活动 前端控制器
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/voucher-activity-info")
public class VoucherActivityInfoController {

    private final VoucherActivityInfoService voucherActivityService;

    public VoucherActivityInfoController(VoucherActivityInfoService voucherActivityService){
        this.voucherActivityService = voucherActivityService;
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public CommonResult<Boolean> add(@RequestBody VoucherActivityParam voucherActivityParam){
        return voucherActivityService.add(voucherActivityParam);
    }

    @ApiOperation("更新")
    @PostMapping("/edit/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id, VoucherActivityParam voucherActivityParam){
        return voucherActivityService.edit(id,voucherActivityParam);
    }

}
