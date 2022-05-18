package com.marketing.activity.controller.b;


import com.marketing.activity.base.CommonPage;
import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.VoucherInfoPageParam;
import com.marketing.activity.domain.param.VoucherInfoParam;
import com.marketing.activity.domain.param.VoucherReceiveDataPageParam;
import com.marketing.activity.domain.resp.VoucherInfoResp;
import com.marketing.activity.domain.resp.VoucherReceiveDataResp;
import com.marketing.activity.domain.resp.VoucherSimpleInfoResp;
import com.marketing.activity.service.b.VoucherInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 券 前端控制器
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */

//@ApiIgnore 使用在类 方法 参数上  屏蔽不在页面上展示
@Api(tags = "B端-券管理")
@RestController
@RequestMapping("/b/voucher-info")
public class VoucherInfoController {

    @Resource
    private VoucherInfoService voucherInfoService;

//    public VoucherInfoController(VoucherInfoService voucherInfoService){
//        this.voucherInfoService = voucherInfoService;
//    }

    @ApiOperation("添加")
    @PostMapping("/add")
    public CommonResult<VoucherSimpleInfoResp> add( @RequestBody VoucherInfoParam voucherInfoParam){
        return voucherInfoService.add(voucherInfoParam);
    }

    @ApiOperation("更新")
    @PutMapping("/edit/{id}")
    public CommonResult<Boolean> edit(@PathVariable("id") Long id, @RequestBody VoucherInfoParam voucherInfoParam) {

        return voucherInfoService.edit(id, voucherInfoParam);
    }

    @ApiOperation("详情")
    @GetMapping("/get/{id}")
    public CommonResult<VoucherInfoResp> get(@PathVariable("id") Long id) {
        return voucherInfoService.get(id);
    }


    @ApiOperation("停止发放")
    @PostMapping("/state/{id}")
    public CommonResult<Boolean> state(@PathVariable("id") Long id){
        return voucherInfoService.state(id);
    }


    @ApiOperation("券列表")
    @PostMapping("/list")
    public CommonResult<CommonPage<VoucherInfoResp>> getList(@RequestBody VoucherInfoPageParam pageParam){
        CommonPage<VoucherInfoResp> commonPage = voucherInfoService.getList(pageParam);
        return CommonResult.success(commonPage);
    }

    @ApiOperation("查看券领券记录")
    @PostMapping("/voucherReceiveData")
    public CommonResult<CommonPage<VoucherReceiveDataResp>> voucherReceiveData(@RequestBody VoucherReceiveDataPageParam pageParam){
       CommonPage<VoucherReceiveDataResp> commonPage = voucherInfoService.voucherReceiveData(pageParam);
       return CommonResult.success(commonPage);
    }


}
