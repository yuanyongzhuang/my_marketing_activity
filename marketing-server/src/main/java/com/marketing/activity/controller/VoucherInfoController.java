package com.marketing.activity.controller;


import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.domain.resp.VoucherSimpleInfoResp;
import com.marketing.activity.service.VoucherInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 券 前端控制器
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */

//@ApiIgnore 使用在类 方法 参数上  屏蔽不在页面上展示
@Api(tags = "券管理")
@RestController
@RequestMapping("/voucher-info")
public class VoucherInfoController {

    private final VoucherInfoService voucherInfoService;

    public VoucherInfoController(VoucherInfoService voucherInfoService){
        this.voucherInfoService = voucherInfoService;
    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public CommonResult<VoucherSimpleInfoResp> add(@RequestBody VoucherInfoParam voucherInfoParam){
        return voucherInfoService.add(voucherInfoParam);
    }

    @ApiOperation("更新")
    @PutMapping("/edit/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id, @RequestBody VoucherInfoParam voucherInfoParam) {

        return voucherInfoService.edit(id, voucherInfoParam);
    }
}
