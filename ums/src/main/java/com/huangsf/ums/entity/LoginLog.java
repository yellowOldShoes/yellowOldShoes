package com.huangsf.ums.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author huangsf
 * @create 2025-01-16  15:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_common_login_log")
public class LoginLog {

    @TableId
    private Long id;
    private String requestIp;
    private Long userId;
    private String userName;
    private String account;
    private String description;
    private LocalDate loginDate;
    private String ua;
    private String browser;
    private String browserVersion;
    private String operatingSystem;
    private String location;
    private LocalDateTime createTime;
    private Long createUser;
}
