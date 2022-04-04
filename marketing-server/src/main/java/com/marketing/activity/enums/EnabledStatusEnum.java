package com.marketing.activity.enums;

/**
 * 启用状态枚举
 * @author yyz
 */

public enum EnabledStatusEnum {

    NO(0, "否"),
    YES(1, "是");

    private Integer value;
    private String desc;

    EnabledStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
