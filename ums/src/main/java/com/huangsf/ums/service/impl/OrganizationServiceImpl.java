package com.huangsf.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.entity.UserOrganization;
import com.huangsf.ums.mapper.sys.OrganizationMapper;
import com.huangsf.ums.service.OrganizationSrevice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huangsf
 * @create 2025-01-16  10:09
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, UserOrganization> implements OrganizationSrevice {
    @Override
    public List<UserOrganization> listOrganization() {
        List<UserOrganization> userOrganizations = baseMapper.listOrganization();
        return generateTree(userOrganizations, 0L);
    }


    private List<UserOrganization> generateTree(List<UserOrganization> organizations,Long parentId){
        List<UserOrganization> children = organizations.stream().filter(organization -> organization.getParentId().equals(parentId)).collect(Collectors.toList());

        if(children==null||children.size()==0){
            return children;
        }
        for(UserOrganization organization:children){
            List<UserOrganization> organizations1 = generateTree(organizations, organization.getId());
            organization.setChildren(organizations1);
        }
        return children;
    }
}
