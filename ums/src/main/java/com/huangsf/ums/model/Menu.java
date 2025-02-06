package com.huangsf.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Getter
@Setter
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 菜单描述
     */
    @TableField("description")
    private String description;

    /**
     * 菜单路径
     */
    @TableField("path")
    private String path;

    /**
     * 父级菜单ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 排序值
     */
    @TableField("sort_value")
    private Integer sortValue;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 是否启用
     */
    @TableField("is_enable")
    private Boolean isEnable;

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

    @TableField(exist = false)
    private List<Menu> children;

}
