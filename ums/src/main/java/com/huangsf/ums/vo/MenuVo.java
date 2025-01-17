package com.huangsf.ums.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-20  9:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel()
public class MenuVo {
    private String key;
    private String title;
    private Long parentId;
    private String path;
    private String describe_;
    private Boolean isEnable;
    private String icon;
    private String component;
    @ApiModelProperty(hidden = true)
    private List<MenuVo> children;
}
