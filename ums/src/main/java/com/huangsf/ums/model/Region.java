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
 * 地区表
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Getter
@Setter
@TableName("region")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 地区ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 地区名称
     */
    @TableField("name")
    private String name;

    /**
     * 父地区ID
     */
    @TableField("parent_id")
    private Long parentId;

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
