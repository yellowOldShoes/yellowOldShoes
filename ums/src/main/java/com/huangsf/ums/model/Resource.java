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
 * 资源表
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Getter
@Setter
@TableName("resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资源名称
     */
    @TableField("name")
    private String name;

    /**
     * 资源类型，如按钮、接口等
     */
    @TableField("type")
    private String type;

    /**
     * 资源描述
     */
    @TableField("description")
    private String description;

    /**
     * 资源路径（如果是接口）
     */
    @TableField("path")
    private String path;

    /**
     * 请求方法（GET, POST, PUT, DELETE）
     */
    @TableField("method")
    private String method;

    /**
     * 资源所属部门ID
     */
    @TableField("department_id")
    private Long departmentId;

    /**
     * 资源敏感级别
     */
    @TableField("sensitivity")
    private String sensitivity;

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


}
