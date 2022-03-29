package com.marketing.activity.base;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * swagger 配置实体
 *
 @EqualsAndHashCode原文中提到的大致有以下几点：
 1.此注解会生成equals(Objectother)和hashCode()方法。
 2.它默认使用非静态，非瞬态的属性
 3.可通过参数exclude排除一些属性
 4.可通过参数of指定仅使用哪些属性
 5.它默认仅使用该类中定义的属性且不调用父类的方法
 6.可通过callSuper=true解决上一点问题。让其生成的equals方法和hashcode方法包含父类属性
  * @author yyz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class SwaggerProperties {

    /**
     * 项目名称
     */
    private String groupName;
    /**
     * API文档生成基础路径
     */
    private String apiBasePackage;
    /**
     * 是否要启用登录认证
     */
    private boolean enableSecurity;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文档版本
     */
    private String version;
    /**
     * 文档联系人姓名
     */
    private String contactName;
    /**
     * 文档联系人网址
     */
    private String contactUrl;
    /**
     * 文档联系人邮箱
     */
    private String contactEmail;
}
