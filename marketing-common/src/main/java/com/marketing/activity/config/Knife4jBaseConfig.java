package com.marketing.activity.config;

import com.marketing.activity.base.SwaggerProperties;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author yyz
 */
public abstract class Knife4jBaseConfig {

    public abstract SwaggerProperties swaggerProperties();

    @Bean
    public Docket defaultApi(){
        SwaggerProperties swaggerProperties = swaggerProperties();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo(swaggerProperties))
                //分组名称
                .groupName(swaggerProperties.getGroupName())
                .select()
                //这里指定controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getApiBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(SwaggerProperties swaggerProperties){
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(new Contact(swaggerProperties.getContactName(),swaggerProperties.getContactUrl(),swaggerProperties.getContactEmail()))
                .version(swaggerProperties.getVersion())
                .build();
    }
}
