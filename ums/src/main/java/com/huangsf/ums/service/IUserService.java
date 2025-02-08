package com.huangsf.ums.service;

import com.huangsf.ums.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huangsf.ums.util.CurrentUser;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
public interface IUserService extends IService<User> {

    List<User> list(CurrentUser currentUser);
}
