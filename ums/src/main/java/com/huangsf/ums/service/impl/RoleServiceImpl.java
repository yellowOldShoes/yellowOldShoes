package com.huangsf.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.dao.RoleMapper;
import com.huangsf.ums.model.Role;
import com.huangsf.ums.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @author huangsf
 * @create 2025-02-07  17:02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
