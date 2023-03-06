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
    public Integer addUser(@Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userEmail") String userEmail, @Param("userPhone") String userPhone);
    //根据邮箱查询邮箱，用于重复邮箱检验
    public String getUserEmail(@Param("userEmail") String userEmail);
    //根据手机号查询手机号，用于重复手机号检验
    public String getUserPhone(@Param("userPhone") String userPhone);
}
