package com.marketing.activity.controller.b;

import cn.hutool.core.lang.Assert;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务健康检测
 * @author yyz
 */
@ApiSupport(author = "yyz",order = 1)
@Api(tags = "服务健康检查")
@RestController
public class HealthyController {

    @ApiOperation("心跳检测")
    @GetMapping("/ping")
    public Object ping(){
//        Assert.isTrue(false,"测试集中异常处理");
        return "ok";
    }
}
