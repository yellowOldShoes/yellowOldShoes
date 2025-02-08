package com.huangsf.ums.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.model.Permission;
import com.huangsf.ums.model.PermissionMenu;
import com.huangsf.ums.model.PermissionResource;
import com.huangsf.ums.service.IPermissionMenuService;
import com.huangsf.ums.service.IPermissionResourceService;
import com.huangsf.ums.service.IPermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    
    @Resource
    IPermissionService permissionService;

    @Resource
    IPermissionResourceService permissionResourceService;

    @Resource
    IPermissionMenuService permissionMenuService;

    @GetMapping
    public BaseResponse list(){
        List<Permission> list = permissionService.list();
        return ResultUtils.success(list);

    }

    @GetMapping("/resources")
    public BaseResponse getResourceIds(@RequestParam("permissionId") Long id){
        List<PermissionResource> list = permissionResourceService.list(new QueryWrapper<PermissionResource>().eq("permission_id", id));
        List<Long> ids = list.stream().map(o -> o.getResourceId()).collect(Collectors.toList());
        return ResultUtils.success(ids);
    }

    /**
     * 给权限分配资源
     * @param permissionId
     * @param resourceIds
     * @return
     */
    @PostMapping("/assignResources")
    public BaseResponse assignResources(@RequestParam("permissionId") Long permissionId,
                                        @RequestParam("resourceIds") Long[] resourceIds){
        List<PermissionResource> collect = Arrays.stream(resourceIds).map((resourceId)->{
            PermissionResource permissionResource = new PermissionResource();
            permissionResource.setPermissionId(permissionId);
            permissionResource.setResourceId(resourceId);
            return permissionResource;
        }).collect(Collectors.toList());

        permissionResourceService.saveBatch(collect);

        return ResultUtils.success("ok");
    }

    @GetMapping("/menus")
    public BaseResponse getMenusIds(@RequestParam("permissionId") Long id){

        List<PermissionMenu> list = permissionMenuService.list(new QueryWrapper<PermissionMenu>().eq("permission_id", id));
        List<Long> ids = list.stream().map(o -> o.getMenuId()).collect(Collectors.toList());
        return ResultUtils.success(ids);
    }

    @PostMapping("/assignMenus")
    public BaseResponse assignMenus(@RequestParam("permissionId")Long permissionId,@RequestParam("menuIds") Long[] menuIds){
        List<PermissionMenu> list = Arrays.stream(menuIds).map((menuId) -> {
            PermissionMenu permissionMenu = new PermissionMenu();
            permissionMenu.setPermissionId(permissionId);
            permissionMenu.setMenuId(menuId);
            return permissionMenu;
        }).collect(Collectors.toList());
        permissionMenuService.saveBatch(list);
        return ResultUtils.success("ok");
    }
}
