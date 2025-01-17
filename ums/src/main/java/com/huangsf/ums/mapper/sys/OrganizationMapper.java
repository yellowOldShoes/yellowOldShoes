package com.huangsf.ums.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangsf.ums.entity.UserOrganization;

import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-16  10:02
 */
public interface OrganizationMapper extends BaseMapper<UserOrganization> {
    List<UserOrganization> listOrganization();
}
