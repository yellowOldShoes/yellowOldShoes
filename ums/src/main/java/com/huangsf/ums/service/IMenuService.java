package com.huangsf.ums.service;

import com.huangsf.ums.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenuByUserId(String token);
}
