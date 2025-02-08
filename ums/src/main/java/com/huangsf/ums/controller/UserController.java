package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.model.User;
import com.huangsf.ums.service.IUserService;
import com.huangsf.ums.util.CurrentUser;
import com.huangsf.ums.util.JwtUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    IUserService userService;

    @PostMapping
    public BaseResponse create(){

        return ResultUtils.success("ok");
    }

    @PutMapping
    public BaseResponse update(){

        return ResultUtils.success("ok");
    }

    public BaseResponse delete(){

        return ResultUtils.success("ok");
    }

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping
    @ApiOperation(value = "用户列表",notes = "属于必须填写的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse list(@RequestHeader("Authorization") String auth){
        CurrentUser currentUser = JwtUtils.getCurrentUser(auth);
        List<User> list = userService.list(currentUser);

        return ResultUtils.success(list);
    }
}
