package com.huangsf.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.entity.Role;
import com.huangsf.ums.mapper.sys.RoleMapper;
import com.huangsf.ums.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-16  14:00
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<Role> listAll() {
        return baseMapper.selectList(new QueryWrapper<Role>());
    }
}
