package com.Service;

import com.Entity.User;
import com.Mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;




    //查询功能

    //根据用户名查询用户密码
    public String getUserPasswordByName(String userName) {
        return userMapper.getUserPasswordByName(userName);
    }

    //根据用户名查询用户id
    public Integer getUserIdByName(String userName) {
        return userMapper.getUserIdByName(userName);
    }
    //根据用户名查询用户名，用于重名检验
    public String getUserNameByName(String userName) {
        return userMapper.getUserNameByName(userName);
    }
    //根据用户名查询邮箱
    public String getUserEmailByName(String userName) {
        return userMapper.getUserEmailByName(userName);
    }
    //根据用户名查询手机号
    public String getUserPhoneByName(String userName) {
        return userMapper.getUserPhoneByName(userName);
    }



    //根据邮箱查询邮箱，用于重复邮箱检验
    public String getUserEmailByEmail(String userEmail) {
        return userMapper.getUserEmailByEmail(userEmail);
    }

    //根据手机号查询手机号，用于重复手机号检验
    public String getUserPhoneByPhone(String userPhone) {
        return userMapper.getUserPhoneByPhone(userPhone);
    }

    //根据用户ID查询用户所有信息
    public User getUserByUserId(Integer userId) {
        return userMapper.getUserByUserId(userId);
    }
    //根据用户ID查询密码
    public String getUserPasswordById(Integer userId) {
        return userMapper.getUserPasswordById(userId);
    }



    //注册功能


    //注册添加用户
    public Integer addUser(String userName, String userPassword, String userEmail, String userPhone) {
        return userMapper.addUser(userName, userPassword, userEmail, userPhone);
    }

    // 修改功能
    //忘记密码
    public Integer updateUserPasswordByName(String userName, String newPassword) {
        return userMapper.updateUserPasswordByName(userName, newPassword);
    }


    //修改用户名
    public Integer updateUserNameById(Integer userId, String userName) {
        return userMapper.updateUserNameById(userId, userName);
    }
    //修改邮箱
    public Integer updateUserEmailById(Integer userId, String userEmail) {
        return userMapper.updateUserEmailById(userId, userEmail);
    }
    //修改手机号
    public Integer updateUserPhoneById(Integer userId, String userPhone) {
        return userMapper.updateUserPhoneById(userId, userPhone);
    }




    //后台功能

    //获得所有用户的信息
    public User[] getUsers(){
        return userMapper.getUsers();
    }





}
