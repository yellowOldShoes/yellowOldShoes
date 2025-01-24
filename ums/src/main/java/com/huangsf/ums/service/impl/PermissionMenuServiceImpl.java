package com.huangsf.ums.service.impl;

import com.huangsf.ums.model.PermissionMenu;
import com.huangsf.ums.dao.PermissionMenuMapper;
import com.huangsf.ums.service.IPermissionMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限与菜单关联表 服务实现类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Service
public class PermissionMenuServiceImpl extends ServiceImpl<PermissionMenuMapper, PermissionMenu> implements IPermissionMenuService {

}
