package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsf
 * @create 2024-12-10  18:24
 */
@Api(tags = "资源接口集")
@RestController
@RequestMapping("/resource")
public class ResourceManagerController {
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse getVisiableResource(@RequestHeader("Authorization") String auth){

        return null;

    }
}
