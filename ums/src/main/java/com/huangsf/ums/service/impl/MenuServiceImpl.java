package com.huangsf.ums.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.dao.PermissionMenuMapper;
import com.huangsf.ums.dao.RolePermissionMapper;
import com.huangsf.ums.dao.UserRoleMapper;
import com.huangsf.ums.model.Menu;
import com.huangsf.ums.dao.MenuMapper;
import com.huangsf.ums.model.PermissionMenu;
import com.huangsf.ums.model.RolePermission;
import com.huangsf.ums.model.UserRole;
import com.huangsf.ums.service.IMenuService;
import com.huangsf.ums.util.CurrentUser;
import com.huangsf.ums.util.JwtUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author huangsf
 * @create 2024-12-17  9:30
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMenuMapper permissionMenuMapper;

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByUserId(String token) {
        // 1) 查询 user_role，拿到该用户所有 roleId
        CurrentUser currentUser = JwtUtils.getCurrentUser(token);

        // 2) 判断是否是超级管理员
        boolean isSuperAdmin = currentUser.isAdmin();
        if (isSuperAdmin) {
            // 直接返回所有菜单(可选：仅返回 is_enable=1 的)
            List<Menu> allMenus = menuMapper.selectList(null);
            return buildMenuTree(allMenus);
        }

        List<Long> roleIds = userRoleMapper.selectList(
                        new QueryWrapper<UserRole>().eq("user_id", currentUser.getId())
                ).stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());

        if (roleIds.isEmpty()) {
            // 用户无角色 => 无菜单
            return Collections.emptyList();
        }

        // 3) role_permission => permissionId 集合
        List<Long> permissionIds = rolePermissionMapper.selectList(
                        new QueryWrapper<RolePermission>().in("role_id", roleIds)
                ).stream()
                .map(RolePermission::getPermissionId)
                .distinct()
                .collect(Collectors.toList());

        if (permissionIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 4) permission_menu => menuId 集合
        List<Long> menuIds = permissionMenuMapper.selectList(
                        new QueryWrapper<PermissionMenu>().in("permission_id", permissionIds)
                ).stream()
                .map(PermissionMenu::getMenuId)
                .distinct()
                .collect(Collectors.toList());

        if (menuIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 5) 在 menu 表中一次性查出这些 menu 记录
        List<Menu> menuList = menuMapper.selectBatchIds(menuIds);
        if (menuList.isEmpty()) {
            return Collections.emptyList();
        }

        // 6) 按 parentId 等信息 构建树形
        return buildMenuTree(menuList);
    }

    /**
     * 通过 parentId 递归或循环构建树
     */
    private List<Menu> buildMenuTree(List<Menu> menuList) {
        // 先把 list 转成 map<id, menu> 以便快速查
        Map<Long, Menu> map = menuList.stream()
                .collect(Collectors.toMap(Menu::getId, m -> m));

        // 准备存储顶级节点
        List<Menu> rootMenus = new ArrayList<>();
        for (Menu menu : menuList) {
            Long parentId = menu.getParentId();
            if (parentId == 0 || !map.containsKey(parentId)) {
                // 如果 parentId=0 或 不在 map 中 => 顶级菜单
                rootMenus.add(menu);
            } else {
                // 找到父菜单, 加到父菜单的 children 列表
                Menu parent = map.get(parentId);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(menu);
            }
        }

        // 根据 sortValue 排序(可选)
        rootMenus.sort(Comparator.comparing(Menu::getSortValue, Comparator.nullsLast(Integer::compare)));

        // 也可递归排序子节点
        return rootMenus;
    }

}
