package com.marketing.activity.config;

import com.marketing.activity.base.SwaggerProperties;
import org.springframework.context.annotation.Configuration;

/**
 * swagger 在线文档
 * http://localhost:8083/doc.html#/home
 * @author yyz
 */
@Configuration
public class Knife4jConfig extends Knife4jBaseConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .groupName("营销活动")
                .apiBasePackage("com.yyz.springNacosCloud.controller")
                .title("营销活动 接口文档")
                .description("# 营销活动 接口文档")
                .contactName("营销活动")
                .version("1.0.0")
                .build();
    }
}
