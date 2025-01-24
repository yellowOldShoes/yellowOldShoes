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
 * 策略规则表
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Getter
@Setter
@TableName("policy")
public class Policy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 策略ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 策略名称
     */
    @TableField("name")
    private String name;

    /**
     * 主体条件（如用户属性）
     */
    @TableField("subject_condition")
    private String subjectCondition;

    /**
     * 资源条件（如资源属性）
     */
    @TableField("resource_condition")
    private String resourceCondition;

    /**
     * 允许的操作（如 read, write, delete）
     */
    @TableField("action")
    private String action;

    /**
     * 策略效果（允许或拒绝）
     */
    @TableField("effect")
    private String effect;

    /**
     * 环境条件（如时间、IP等）
     */
    @TableField("environment_condition")
    private String environmentCondition;

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
