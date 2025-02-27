package com.huangsf.ums.dao;

import com.huangsf.ums.model.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getVisiableMenu(Long userId);
}
