package com.huangsf.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @TableField("account")
    private String account;

    /**
     * 姓名
     */
    @TableField("name")
    private String name;

    /**
     * 用户所属部门ID
     */
    @TableField("department_id")
    private Long departmentId;

    /**
     * 用户职位
     */
    @TableField("position")
    private String position;

    /**
     * 用户所属地区ID
     */
    @TableField("region_id")
    private Long regionId;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 启用状态 1启用 0禁用
     */
    @TableField("status")
    private Boolean status;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 工作描述
     */
    @TableField("work_describe")
    private String workDescribe;

    /**
     * 密码哈希值
     */
    @TableField("password_hash")
    private String passwordHash;

    /**
     * 最后登录时间
     */
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
