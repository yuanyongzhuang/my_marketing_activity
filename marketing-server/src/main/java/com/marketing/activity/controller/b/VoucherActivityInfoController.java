package com.marketing.activity.controller.b;


import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.VoucherActivityPageParam;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.domain.resp.VoucherActivityInfoResp;
import com.marketing.activity.service.b.VoucherActivityInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 券活动 前端控制器
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
//@ApiIgnore隐藏api
@Api(tags = "B端-券活动")
@RestController
@RequestMapping("/b/voucher-activity-info")
public class VoucherActivityInfoController {

    @Resource
    private VoucherActivityInfoService voucherActivityService;

//    public VoucherActivityInfoController(VoucherActivityInfoService voucherActivityService){
//        this.voucherActivityService = voucherActivityService;
//    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public CommonResult<Boolean> add(@RequestBody VoucherActivityParam voucherActivityParam){
        return voucherActivityService.add(voucherActivityParam);
    }

    @ApiOperation("更新")
    @PutMapping("/edit/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id, @RequestBody VoucherActivityParam voucherActivityParam){
        return voucherActivityService.edit(id,voucherActivityParam);
    }

    @ApiOperation("查看详情")
    @GetMapping("/get/{id}")
    public CommonResult<VoucherActivityInfoResp> get(@PathVariable("id") Long id){
        return voucherActivityService.get(id);
    }

    @ApiOperation("/停止使用")
    @PostMapping("/state/{id}")
    public CommonResult<Boolean> state(@PathVariable("id") Long id){
        return voucherActivityService.state(id);
    }

    @ApiOperation("活动列表")
    @PostMapping("/list")
    public CommonResult<CommonPage<VoucherActivityInfoResp>> getList(@RequestBody VoucherActivityPageParam pageParam){
        CommonPage<VoucherActivityInfoResp> resultPage = voucherActivityService.getList(pageParam);
        return CommonResult.success(resultPage);
    }


}
