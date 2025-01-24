package com.huangsf.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Casbin rbac 策略表
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Getter
@Setter
@TableName("casbin_rule")
public class CasbinRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 策略类型（如 p 或 g）
     */
    @TableField("ptype")
    private String ptype;

    /**
     * 主体（用户/角色）或用户-角色映射
     */
    @TableField("v0")
    private String v0;

    /**
     * 资源（如 URL、菜单标识）
     */
    @TableField("v1")
    private String v1;

    /**
     * 操作（read、write、delete）
     */
    @TableField("v2")
    private String v2;

    /**
     * 可扩展字段（比如 domain）
     */
    @TableField("v3")
    private String v3;

    /**
     * 可扩展字段
     */
    @TableField("v4")
    private String v4;

    /**
     * 可扩展字段
     */
    @TableField("v5")
    private String v5;


}
