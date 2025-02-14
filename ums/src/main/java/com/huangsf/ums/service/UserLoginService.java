package com.huangsf.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huangsf.ums.dto.RegisterDto;
import com.huangsf.ums.model.User;
import com.huangsf.ums.vo.LoginVo;

/**
 * @author huangsf
 * @create 2024-12-06  13:45
 */

public interface UserLoginService extends IService<User> {

    long userRegister(RegisterDto registerDto);

    LoginVo login(String username, String password, String captcha);




}
