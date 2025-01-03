package com.huangsf.ums.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huangsf
 * @create 2024-12-20  9:33
 */
@Data
@AllArgsConstructor
public class MenuVo {
    private Long id;
    private String name;
    private String path;
    private String component;
    private String icon;
}
