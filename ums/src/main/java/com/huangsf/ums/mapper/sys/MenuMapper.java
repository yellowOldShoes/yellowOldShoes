package com.huangsf.ums.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangsf.ums.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-16  13:57
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> getVisiableMenu(@Param("userId") Long userId);
}
