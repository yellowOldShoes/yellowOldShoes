package com.huangsf.ums.constant;

/**
 * 系统角色类
 * @author huangsf
 * @create 2024-11-28  17:22
 */
public enum SysRole {

    ADMIN("系统管理员","权限最高"),
    LEADER("管理人员","权限仅次于系统管理员,能够查看其下人员的数据"),
    STAFF("普通工作人员","只能查看各自的数据");

    private String name;
    private String description;
    private SysRole(String name,String description){
        this.name = name;
        this.description = description;
    }
}
