package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.entity.UserOccupation;
import com.huangsf.ums.service.OccupationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author huangsf
 * @create 2025-01-08  15:07
 */

@RestController
@RequestMapping("/occupation")
@Api(tags = "岗位接口")
public class OccupationController {

    @Resource
    OccupationService occupationService;

    /**
     * 新增
     */
    @PostMapping
    public BaseResponse createOccupation(){

        return ResultUtils.success("");
    }

    /**
     * 修改
     */
    @PutMapping
    public BaseResponse modifyOccupation(){

        return ResultUtils.success("======");
    }


    /**
     * 删除
     */
    @DeleteMapping
    public BaseResponse deleteOccupation(){

        return ResultUtils.success("");
    }


    /**
     * 列表
     */
    @GetMapping
    @ApiOperation(value = "获取所有岗位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse listOccupation(){
        List<UserOccupation> userOccupations = occupationService.listOccupation();
        return ResultUtils.success(userOccupations);
    }

    @GetMapping("/{id}")
    public BaseResponse getOccupation(){

        return ResultUtils.success("");
    }
}
