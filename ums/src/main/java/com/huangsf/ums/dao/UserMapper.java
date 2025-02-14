package com.huangsf.ums.dao;

import com.huangsf.ums.model.Role;
import com.huangsf.ums.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Role> listRoles(@Param("userId") Long userId);
}
