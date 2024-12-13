package com.huangsf.ums.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author huangsf
 * @create 2024-12-02  16:30
 */
@Data
@ApiModel(value = "用户注册传输对象")
public class RegisterDto {
    @ApiModelProperty(value = "账号",name = "account")
    private String account;

    @ApiModelProperty(value = "密码",name = "password")
    private String password;

    @ApiModelProperty(value = "二次密码",name = "checkPassword")
    private String checkPassword;

    @ApiModelProperty(value = "手机号码",name = "mobile")
    private String mobile;

}
