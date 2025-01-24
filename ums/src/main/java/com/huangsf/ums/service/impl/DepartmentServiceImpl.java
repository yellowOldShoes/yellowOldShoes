package com.huangsf.ums.service.impl;

import com.huangsf.ums.model.Department;
import com.huangsf.ums.dao.DepartmentMapper;
import com.huangsf.ums.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
