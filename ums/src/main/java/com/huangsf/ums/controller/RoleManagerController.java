package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.entity.Role;
import com.huangsf.ums.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-10  18:24
 */
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/role")
public class RoleManagerController {

    @Resource
    RoleService roleService;

    @ApiOperation("角色列表")
    @GetMapping
    public BaseResponse listRole(){
        List<Role> roles = roleService.listAll();
        return ResultUtils.success(roles);
    }

}
