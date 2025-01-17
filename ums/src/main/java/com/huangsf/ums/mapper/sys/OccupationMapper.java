package com.huangsf.ums.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangsf.ums.entity.UserOccupation;

import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-15  17:06
 */
public interface OccupationMapper extends BaseMapper<UserOccupation> {
    List<UserOccupation> listOccupation();
}
