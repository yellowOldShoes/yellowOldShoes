package com.huangsf.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huangsf.ums.dao.UserMapper;
import com.huangsf.ums.dto.UserDto;
import com.huangsf.ums.model.Role;
import com.huangsf.ums.model.User;
import com.huangsf.ums.service.IUserService;
import com.huangsf.ums.util.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import static com.huangsf.ums.constant.SystemConstant.SALT;

/**
 * @author huangsf
 * @create 2025-02-10  9:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public List<User> list(CurrentUser currentUser) {
    //        this.baseMapper.selectById(currentUser.getId())
            //1 当前用户如果是管理员能看到所有人员

            //2 不是管理员只能看到自己创建的人员


            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//            userQueryWrapper.eq("status", 1);
            return baseMapper.selectList(userQueryWrapper);
        }

    @Override
    public void createUser(UserDto userDto) {

        //默认密码
        String password = "000000";
        if(StringUtils.isNotBlank(userDto.getPasswordHash())){
            password = userDto.getPasswordHash();
        }

        String passwordHash = DigestUtils.md5DigestAsHex((password + SALT).getBytes());

        //过期时间
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(7);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Instant instant = zonedDateTime.toInstant();
        Date from = Date.from(instant);

        User user = new User();
        user.setPasswordHash(passwordHash);
        BeanUtil.copyProperties(userDto, user, "passwordHash");
        user.setPasswordExpireTime(from);
        baseMapper.insert(user);
    }

    @Override
    public boolean updateUser(UserDto userDto,Long id) {
        baseMapper.update(userDto, new UpdateWrapper<User>().eq("id", id));
        return true;
    }

    @Override
    public List<Role> listRoles(Long userId) {
        return baseMapper.listRoles(userId);
    }


}
