package com.huangsf.ums.vo;

import lombok.Data;

/**
 * @author huangsf
 * @create 2024-12-11  15:13
 */
@Data
public class LoginVo {
    private String token; //用户token
    private String info;  //提示信息
}
