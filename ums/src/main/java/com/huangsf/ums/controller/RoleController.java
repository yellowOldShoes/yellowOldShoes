package com.huangsf.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.model.Department;
import com.huangsf.ums.model.Role;
import com.huangsf.ums.model.RolePermission;
import com.huangsf.ums.service.IRolePermissionService;
import com.huangsf.ums.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    IRoleService roleService;

    @Resource
    IRolePermissionService rolePermissionService;

    @GetMapping
    public BaseResponse list(){
        List<Role> list = roleService.list();
        List<Role> roles = buildMenuTree(list);
        return ResultUtils.success(roles);
    }

    @PostMapping
    public BaseResponse create(@RequestBody Role role){
        roleService.save(role);
        return ResultUtils.success("ok");
    }

    @GetMapping("/permissions")
    public BaseResponse getPermissions(@RequestParam("roleId") Long roleId){

        List<RolePermission> rolePermissions = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        List<Long> list = rolePermissions.stream().map(o -> o.getPermissionId()).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    @PostMapping("/assignPermissions")
    public BaseResponse assignPermission(@RequestParam("roleId") Long roleId,@RequestParam("permissionIds") Long[] permissionIds){

        List<RolePermission> list = Arrays.stream(permissionIds).map(o -> {
            RolePermission roleP = new RolePermission();
            roleP.setPermissionId(o);
            roleP.setRoleId(roleId);
            return roleP;
        }).collect(Collectors.toList());
        rolePermissionService.saveBatch(list);
        return ResultUtils.success("ok");
    }





    /**
     * 通过 parentId 递归或循环构建树
     */
    private List<Role> buildMenuTree(List<Role> menuList) {
        // 先把 list 转成 map<id, menu> 以便快速查
        Map<Long, Role> map = menuList.stream()
                .collect(Collectors.toMap(Role::getId, m -> m));

        // 准备存储顶级节点
        List<Role> rootMenus = new ArrayList<>();
        for (Role menu : menuList) {
            Long parentId = menu.getParentRoleId();
            if (parentId == 0 || !map.containsKey(parentId)) {
                // 如果 parentId=0 或 不在 map 中 => 顶级菜单
                rootMenus.add(menu);
            } else {
                // 找到父菜单, 加到父菜单的 children 列表
                Role parent = map.get(parentId);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(menu);
            }
        }

        // 也可递归排序子节点
        return rootMenus;
    }


}
