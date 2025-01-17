package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.entity.UserOrganization;
import com.huangsf.ums.service.OrganizationSrevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-08  15:08
 */
@RestController
@RequestMapping(("/organization"))
@Api(tags = "组织接口")
public class OrganizationController {

    @Resource
    OrganizationSrevice organizationSrevice;

    /**
     * 新增
     */


    /**
     * 修改
     */


    /**
     * 删除
     */


    /**
     * 列表
     */
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    @ApiOperation(value = "列表接口")
    public BaseResponse listOrgranization(){
        List<UserOrganization> userOrganizations = organizationSrevice.listOrganization();
        return ResultUtils.success(userOrganizations);
    }

}
