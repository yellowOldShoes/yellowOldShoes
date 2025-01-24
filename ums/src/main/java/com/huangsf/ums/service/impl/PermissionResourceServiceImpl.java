package com.huangsf.ums.service.impl;

import com.huangsf.ums.model.PermissionResource;
import com.huangsf.ums.dao.PermissionResourceMapper;
import com.huangsf.ums.service.IPermissionResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限与资源关联表 服务实现类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Service
public class PermissionResourceServiceImpl extends ServiceImpl<PermissionResourceMapper, PermissionResource> implements IPermissionResourceService {

}
