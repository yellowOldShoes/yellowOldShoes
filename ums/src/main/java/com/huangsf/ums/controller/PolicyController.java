package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.model.Policy;
import com.huangsf.ums.service.IPolicyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;
import java.util.List;

/**
 * <p>
 * 策略规则表 前端控制器
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Resource
    IPolicyService policyService;

    @GetMapping
    public BaseResponse list(){
        List<Policy> list = policyService.list();
        return ResultUtils.success(list);
    }

    @PostMapping
    public BaseResponse create(@RequestBody Policy policy){

        policyService.save(policy);
        return ResultUtils.success("ok");
    }


    @PutMapping
    public BaseResponse update(){

        return ResultUtils.success("");
    }


    @DeleteMapping
    public BaseResponse delete(){

        return ResultUtils.success("");
    }
}
