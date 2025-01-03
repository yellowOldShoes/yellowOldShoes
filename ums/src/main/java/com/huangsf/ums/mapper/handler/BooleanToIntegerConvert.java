package com.huangsf.ums.mapper.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author huangsf
 * @create 2024-12-11  17:32
 */
public class BooleanToIntegerConvert extends BaseTypeHandler<Boolean> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Boolean aBoolean, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, aBoolean ? 1 : 0);
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int value = resultSet.getInt(s);
        return value != 0;
    }

    @Override
    public Boolean getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int value = resultSet.getInt(i);
        return value != 0;
    }

    @Override
    public Boolean getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int value = callableStatement.getInt(i);
        return value != 0;
    }


}
