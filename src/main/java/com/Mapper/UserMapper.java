package com.Mapper;

import com.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    //查询功能

    //根据用户名查询用户密码
    public String getUserPasswordByName(@Param("userName") String userName);
    //根据用户名查询用户id
    public Integer getUserIdByName(@Param("userName") String userName);
    //根据用户名查询用户名，用于重名检验
    public String getUserNameByName(@Param("userName") String userName);
    //根据用户名查询用户邮箱
    public String getUserEmailByName(@Param("userName") String userName);
    //根据用户名查询用户手机号
    public String getUserPhoneByName(@Param("userName") String userName);


    //注册功能

    //注册添加用户
    public Integer addUser(@Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userEmail") String userEmail, @Param("userPhone") String userPhone);
    //根据邮箱查询邮箱，用于重复邮箱检验
    public String getUserEmailByEmail(@Param("userEmail") String userEmail);
    //根据手机号查询手机号，用于重复手机号检验
    public String getUserPhoneByPhone(@Param("userPhone") String userPhone);
    //获得所有用户信息
    public User[] getUsers();


    //修改功能
    public void updateUserPasswordByName(@Param("userName") String userName,
                                    @Param("newPassword") String newPassword);

}
