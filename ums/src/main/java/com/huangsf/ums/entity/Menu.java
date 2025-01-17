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
import java.util.List;

/**
 * Entity for the "menu" table
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("menu")
public class Menu implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long key;

    @TableField(value = "name")
    private String title;

    @TableField(value="describe_")
    private String describe;

    private Boolean isPublic;

    private String path;

    private String component;

    private Boolean isEnable;

    private Integer sortValue;

    private String icon;

    private String redirect;

    private String group;

    private Long parentId;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    @TableField(exist = false)
    private List<Menu> children;
}