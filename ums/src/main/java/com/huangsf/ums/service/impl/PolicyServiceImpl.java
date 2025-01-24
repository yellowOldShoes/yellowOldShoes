package com.huangsf.ums.service.impl;

import com.huangsf.ums.model.Policy;
import com.huangsf.ums.dao.PolicyMapper;
import com.huangsf.ums.service.IPolicyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 策略规则表 服务实现类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Service
public class PolicyServiceImpl extends ServiceImpl<PolicyMapper, Policy> implements IPolicyService {

}
