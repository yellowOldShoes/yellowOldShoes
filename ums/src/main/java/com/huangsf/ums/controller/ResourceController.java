package com.huangsf.ums.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.model.ResourceEntity;
import com.huangsf.ums.service.IResourceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资源表 前端控制器
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    IResourceService resourceService;

    @PostMapping
    public BaseResponse createResource(@RequestBody ResourceEntity resource){
        boolean save = resourceService.save(resource);
        return ResultUtils.success(save);
    }

    @GetMapping
    public BaseResponse list(){
        List<ResourceEntity> list = resourceService.list();
        return ResultUtils.success(list);
    }



}
