package com.huangsf.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huangsf.ums.dto.RegisterDto;
import com.huangsf.ums.model.User;
import com.huangsf.ums.util.CurrentUser;
import com.huangsf.ums.vo.LoginVo;

import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-06  13:45
 */

public interface UserService extends IService<User> {

    long userRegister(RegisterDto registerDto);

    LoginVo login(String username, String password, String captcha);




}
