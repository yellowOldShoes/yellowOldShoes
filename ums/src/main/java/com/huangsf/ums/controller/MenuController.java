package com.huangsf.ums.controller;

import cn.hutool.core.bean.BeanUtil;
import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.entity.Menu;
import com.huangsf.ums.service.MenuService;
import com.huangsf.ums.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-17  9:33
 */
@Api(tags = "菜单接口集")
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Resource
    MenuService menuService;

    @GetMapping
    @ApiOperation(value = "菜单按钮",notes = "属于必须填写的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse getTreeMenu(@RequestHeader("Authorization") String auth){
        List<Menu> menus = menuService.listAllMenu(auth);
        List<MenuVo> menuVos = copyProperties(menus);
        BaseResponse<List<MenuVo>> success = ResultUtils.success(menuVos);
        return success;
    }

    private List<MenuVo> copyProperties(List<Menu> menus){

        List<MenuVo> list = new ArrayList<>();
        for(Menu menu:menus){
            MenuVo menuVo = new MenuVo();
            menuVo.setKey(menu.getKey().toString());
            menuVo.setTitle(menu.getTitle());
            menuVo.setPath(menu.getPath());
            menuVo.setDescribe_(menu.getDescribe());
            menuVo.setParentId(menu.getParentId());
            menuVo.setIsEnable(menu.getIsEnable());
            menuVo.setComponent(menu.getComponent());
            menuVo.setIcon(menu.getIcon());
            List<Menu> children = menu.getChildren();
            if(children.size()>0){
                List<MenuVo> menuVos = copyProperties(children);
                menuVo.setChildren(menuVos);
            }
            list.add(menuVo);
        }

        return list;
    }

    @PostMapping
    @ApiOperation(value = "新增菜单",notes = "属于必须填写的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse create(@RequestBody MenuVo menuVo){
        Menu menu = new Menu();
        BeanUtil.copyProperties(menuVo, menu);
        boolean save = menuService.save(menu);
        return ResultUtils.success("新增成功");
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse update(@PathVariable Long id,@RequestBody MenuVo menuvo){
//        Menu menu = new Menu();
//        BeanUtil.copyProperties(menuvo, menu);
        Menu menu = BeanUtil.copyProperties(menuvo, Menu.class, "key");
        menu.setKey(id);
        boolean b = menuService.updateById(menu);
        return ResultUtils.success("更新成功:"+b);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除指定菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse delete(@PathVariable Long id){
        boolean b = menuService.removeById(id);
        return ResultUtils.success("删除"+b);
    }


}
