package com.huangsf.ums.common;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @author huangsf
 * @create 2024-12-11  15:54
 */
public enum StatusEnum {
    ACTIVE(1,"启用"),
    INACTIVE(0,"禁用");
    @EnumValue
    private final int code;
    private final String desc;

    StatusEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
