package com.huangsf.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huangsf.ums.entity.Menu;

import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-17  9:29
 */
public interface MenuService extends IService<Menu> {

    List<Menu> listAllMenu(String token);
}
