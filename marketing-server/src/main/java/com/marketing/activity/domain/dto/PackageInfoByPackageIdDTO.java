package com.marketing.activity.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author yyz
 */
@Data
public class PackageInfoByPackageIdDTO implements Serializable {

    /**
     * 套餐id
     */
    private Integer packageId;
    /**
     * 目录名称
     */
    private String directoryName;
    /**
     * 二级目录名称
     */
    private String examName;
    /**
     * 三级目录名称
     */
    private String subjectName;
    /**
     * 套才能方向名称
     */
    private String packageName;
    /**
     * 课程套餐类型
     */
    private String packageTypeName;
    /**
     * 价格
     */
    private BigDecimal packagePrice;
    /**
     * 项目类ID
     */
    private Integer directoryId;
    /**
     * 项目ID
     */
    private Integer examId;
    /**
     * 科目ID
     */
    private Integer subjectId;
    /**
     * 套餐类型
     */
    private Integer packageType;
    /**
     * 原价
     */
    private BigDecimal originalPrice;
    /**
     * 协议模板ID
     */
    private Integer xieYiTemplateId;
    /**
     * 退费协议模板ID
     */
    private Integer tuiFeiXieYiTemplateId;
    /**
     * 是否是面授套餐
     */
    private Integer isMianShou;
    /**
     * 是否是高端班
     */
    private Integer isGaoDuanBan;
    /**
     * 是否是最低价课 0否 1是
     */
    private Integer isDiJiaClass;
    /**
     * 上课模式：0常规课程模式 1约课模式
     */
    private Integer classMode;
    /**
     * 最低出售价格
     */
    private BigDecimal minPackagePrice;
    /**
     * 协议id
     */
    private Integer agreementId;
    /**
     * 商品有效期：0非考季 1考季
     */
    private Integer termOfValidity;
    /**
     * 仓库状态
     */
    private String isActive;
    /**
     * 有无学管师服务
     */
    private Integer isXgsService;
    /**
     * 是否包含一对一服务课程：0没有 1有
     */
    private Integer isOneToOneService;
    /**
     * 一对一服务次数
     */
    private Integer oneToOneServiceCount;
    /**
     * 商品发证机构
     */
    private String issuingAuthority;
    /**
     * 最低算薪价格
     */
    private BigDecimal minimumSalaryPrice;
    /**
     * 商品属性 0全科 1单科
     */
    private Integer packageProperty;
}
