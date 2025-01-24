package com.huangsf.ums.service.impl;

import com.huangsf.ums.model.Permission;
import com.huangsf.ums.dao.PermissionMapper;
import com.huangsf.ums.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
