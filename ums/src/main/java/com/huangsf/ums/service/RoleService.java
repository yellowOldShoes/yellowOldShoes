package com.huangsf.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huangsf.ums.entity.Role;

import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-16  13:55
 */
public interface RoleService extends IService<Role> {
    List<Role> listAll();
}
