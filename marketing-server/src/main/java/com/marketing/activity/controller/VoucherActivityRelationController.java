package com.marketing.activity.controller;


import com.marketing.activity.base.CommonResult;
import com.marketing.activity.domain.param.VoucherActivityParam;
import com.marketing.activity.helper.VoucherActivityRelationHelper;
import com.marketing.activity.service.VoucherActivityRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 卡券活动关联 前端控制器
 * </p>
 *
 * @author yyz
 * @since 2022-03-29
 */
@ApiIgnore
@Api(tags = "券运营活动管理")
@RestController
@RequestMapping("/voucher-activity-relation")
public class VoucherActivityRelationController {
}
