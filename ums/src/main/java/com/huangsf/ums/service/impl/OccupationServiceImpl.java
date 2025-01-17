package com.huangsf.ums.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.entity.UserOccupation;
import com.huangsf.ums.mapper.sys.OccupationMapper;
import com.huangsf.ums.service.OccupationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-15  17:08
 */
@Service
public class OccupationServiceImpl extends ServiceImpl<OccupationMapper, UserOccupation> implements OccupationService {

    @Override
    public List<UserOccupation> listOccupation() {
        return baseMapper.listOccupation();
    }
}
