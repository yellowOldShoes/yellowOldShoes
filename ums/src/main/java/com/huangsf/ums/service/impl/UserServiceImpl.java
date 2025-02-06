package com.huangsf.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.common.ErrorCode;
import com.huangsf.ums.dto.RegisterDto;
import com.huangsf.ums.model.User;
import com.huangsf.ums.exception.BusinessException;
import com.huangsf.ums.dao.UserMapper;
import com.huangsf.ums.service.UserService;
import com.huangsf.ums.util.CurrentUser;
import com.huangsf.ums.util.JwtUtils;
import com.huangsf.ums.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.*;
import java.util.Date;
import java.util.List;

import static com.huangsf.ums.constant.SystemConstant.SALT;

/**
 * @author huangsf
 * @create 2024-12-06  13:46
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    JwtUtils jwtUtils;
    @Transactional
    public long userRegister(RegisterDto registerDto) {

        if(StringUtils.isBlank(registerDto.getAccount())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号必填");
        }

        if(StringUtils.isBlank(registerDto.getPassword())){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码必填");
        }

        // 1.账号不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", registerDto.getAccount());
        long count = this.baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名重复");
        }
        // 2. 生成用户信息
        String digPassword = DigestUtils.md5DigestAsHex((registerDto.getPassword() + SALT).getBytes());
        String checkDigPwd = DigestUtils.md5DigestAsHex((registerDto.getCheckPassword() + SALT).getBytes());
        if(!StringUtils.equals(digPassword, checkDigPwd)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }
        User user = new User();
        BeanUtils.copyProperties(registerDto, user);
        user.setPasswordHash(digPassword);

        //设置有效期两个月
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(7);
        ZoneId zoneId = ZoneId.systemDefault();

        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        Date from = Date.from(instant);

        user.setPasswordExpireTime(from);
        user.setCreateTime(new Date());
        user.setCreateTime(new Date());

        // 3.保存用户数据
        boolean save = this.save(user);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }

        return user.getId();
    }

    public LoginVo login(String account, String password, String captcha) {

        // 1.参数是否正确
        if(StringUtils.isAnyBlank(account,password)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号、密码不能为空!");
        }

        if(captcha==null||!captcha.equals(stringRedisTemplate.opsForValue().get(captcha))){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "验证码错误，请重新输入");
        }

        String digestPwd = DigestUtils.md5DigestAsHex((password + SALT).getBytes());
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", account).eq("password_hash", digestPwd);
        User user = this.baseMapper.selectOne(userQueryWrapper);
        if(user==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码错误");
        }

        if (user.getStatus()){
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "用户被禁用，请联系管理员");
        }

        // 用户密码快过期的时候提醒用户
        String info = "";
        Date passwordExpireTime = user.getPasswordExpireTime();
        if(passwordExpireTime!=null){
            Instant instant = passwordExpireTime.toInstant();
            ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
            LocalDateTime expireLocalTime = zonedDateTime.toLocalDateTime();
            Duration between = Duration.between(expireLocalTime, LocalDateTime.now());
            long days = Math.abs(between.toDays());

            if(days<=7){
                info = "你的密码还有"+days+"天过期，请更改密码!";
            }
        }


        // 4.生成token并写入redis
        CurrentUser currentUser = new CurrentUser(user.getId(), user.getAccount(), user.getName(),user.isAdmin());
        String token = jwtUtils.loginSign(currentUser, digestPwd);
        LoginVo loginVo = new LoginVo();
        loginVo.setInfo(info);
        loginVo.setToken(token);
        return loginVo;
    }

    @Override
    public List<User> list(CurrentUser currentUser) {
//        this.baseMapper.selectById(currentUser.getId())
        //1 当前用户如果是管理员能看到所有人员

        //2 不是管理员只能看到自己创建的人员


        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("status", 0);
        return baseMapper.selectList(userQueryWrapper);
    }
}
