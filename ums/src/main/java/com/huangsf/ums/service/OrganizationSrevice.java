package com.huangsf.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huangsf.ums.entity.UserOccupation;
import com.huangsf.ums.entity.UserOrganization;

import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-16  10:08
 */
public interface OrganizationSrevice extends IService<UserOrganization> {
    List<UserOrganization> listOrganization();
}
