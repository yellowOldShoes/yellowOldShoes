package com.huangsf.ums.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.entity.Menu;
import com.huangsf.ums.mapper.sys.MenuMapper;
import com.huangsf.ums.service.MenuService;
import com.huangsf.ums.util.CurrentUser;
import com.huangsf.ums.util.JwtUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author huangsf
 * @create 2024-12-17  9:30
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {


    @Override
    public List<Menu> listAllMenu(String token) {
        CurrentUser currentUser = JwtUtils.getCurrentUser(token);
        List<Menu> visiableMenu = baseMapper.getVisiableMenu(currentUser.getId());
        return generatorTree(visiableMenu, 0L);
    }

    private List<Menu> generatorTree(List<Menu> menus,Long parentId){
        //--------------------可行------------
//        List<Menu> children = menus.stream().filter(menu -> menu.getParentId().equals(parentId)).collect(Collectors.toList());
//
//        if(children==null||children.size()==0){
//            return children;
//        }
//        for(Menu menu:children){
//            List<Menu> menus1 = generatorTree(menus, menu.getId());
//            menu.setChildren(menus1);
//        }
//        return children;

        // 通过 groupingBy 根据父ID进行分组
        Map<Long, List<Menu>> menuMap = menus.stream()
                .collect(Collectors.groupingBy(Menu::getParentId));

        //---------------也可行----------------
        // 获取父级菜单的所有子菜单
        List<Menu> children = menuMap.getOrDefault(parentId, Collections.emptyList());

        // 对每个子菜单进行递归构建子菜单列表
        children.forEach(menu -> {
            List<Menu> subMenus = generatorTree(menus, menu.getId());
            menu.setChildren(subMenus); // 为当前菜单设置子菜单
        });

        return children;
    }
}
