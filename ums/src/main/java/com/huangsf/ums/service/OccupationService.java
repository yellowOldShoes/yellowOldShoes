package com.huangsf.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huangsf.ums.entity.UserOccupation;

import java.util.List;

/**
 * @author huangsf
 * @create 2025-01-15  17:06
 */

public interface OccupationService extends IService<UserOccupation> {
    List<UserOccupation> listOccupation();
}
