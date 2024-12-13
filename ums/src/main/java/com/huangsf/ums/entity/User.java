package com.huangsf.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity for the "user" table
 */
@Data
@TableName("user")
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String account;

    private String name;

    private Long orgId;

    private Long stationId;

    private String email;

    private String mobile;

    private String sex;

    private Boolean status;

    private String avatar;

    private String workDescribe;

    private Date passwordErrorLastTime;

    private Integer passwordErrorNum;

    private String passCode;

    private Date passwordExpireTime;

    private String password;

    private Date lastLoginTime;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;
}