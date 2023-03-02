package com.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    //根据用户名查询用户密码
    public String getUserPassword(@Param("userName") String userName);
    //根据用户名查询用户名，用于重名检验
    public String getUserName(@Param("userName") String userName);
    //注册添加用户
    public Integer addUser(@Param("userName") String userName, @Param("userPassword") String password);
}
