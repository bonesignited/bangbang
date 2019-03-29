package com.zlx.bangbang.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumNameTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private Class<E> type;

    private E[] enums;

    public EnumNameTypeHandler() {
    }

    public EnumNameTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() +
                    " does not represent an enum type");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, e.name());
    }

    @Override
    public E getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String name = resultSet.getString(s);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(name);
        }
    }

    @Override
    public E getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String name = resultSet.getString(i);
        if (resultSet.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(name);
        }
    }

    @Override
    public E getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String name = callableStatement.getString(i);
        if (callableStatement.wasNull()) {
            return null;
        } else {
            return locateEnumStatus(name);
        }
    }


    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
     * @param name Enum 类型的 ordinal 属性
     * @return ordinal 对应的枚举类
     */
    private E locateEnumStatus(String name) {
        for(E e : enums) {
            if(e.name().equals(name)) {
                return e;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + name + ",请核对" + type.getSimpleName());
    }
}
