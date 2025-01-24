package com.huangsf.ums.service.impl;

import com.huangsf.ums.model.Resource;
import com.huangsf.ums.dao.ResourceMapper;
import com.huangsf.ums.service.IResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
