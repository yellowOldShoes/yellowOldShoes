package com.huangsf.ums.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huangsf
 * @create 2024-12-09  13:44
 */
@Data
@AllArgsConstructor
public class CurrentUser {
    private Long id;
    private String account;
    private String name;
//    private String role;

    private boolean isAdmin;
}
