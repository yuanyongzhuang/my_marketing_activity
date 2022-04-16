package com.marketing.activity.enums;

public enum UserVoucherStatusEnum {
    UNUSED(0,"未使用"),
    USED(1,"已使用"),
    EXPIRED(2,"已过期");

    Integer value;
    String desc;

    UserVoucherStatusEnum(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue(){
        return value;
    }
    public String getDesc(){
        return desc;
    }

}
