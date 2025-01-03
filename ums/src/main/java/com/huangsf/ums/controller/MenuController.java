package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.entity.Menu;
import com.huangsf.ums.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-17  9:33
 */
@Api(tags = "菜单接口集")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    MenuService menuService;

    @GetMapping("/getAllMenu")
    @ApiOperation(value = "菜单按钮",notes = "属于必须填写的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse listAllMenu(@RequestHeader("Authorization") String auth){
        List<Menu> menus = menuService.listAllMenu(auth);

        BaseResponse<List<Menu>> success = ResultUtils.success(menus);
        return success;
    }

    @GetMapping("/getChildMenu")
    @ApiOperation(value = "获取子菜单")
    public BaseResponse findChildMenu(){

        return null;
    }


}
