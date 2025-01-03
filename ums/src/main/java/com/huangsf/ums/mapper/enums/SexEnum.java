package com.huangsf.ums.mapper.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author huangsf
 * @create 2024-12-16  9:35
 */
@Getter
@AllArgsConstructor
public enum SexEnum {
    MALE("M","男"),
    FEMALE("F","女");
    @EnumValue
    private String code;
    private String value;
}

