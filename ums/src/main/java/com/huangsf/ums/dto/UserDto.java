package com.huangsf.ums.dto;

import com.huangsf.ums.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangsf
 * @create 2025-02-10  10:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends User {
    private String account;
    private String name;
    private String passwordHash;
    private String email;
    private Boolean status;
}
