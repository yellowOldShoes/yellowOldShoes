package com.huangsf.ums.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity for the "user_occupation" table
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_occupation")
public class UserOccupation implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @TableField(exist = false)
    private String organizationName;

    private Long orgId;

    private Boolean status;

    private String describe;

    private Date createTime;

    private Long createUser;

    private Date updateTime;

    private Long updateUser;
}