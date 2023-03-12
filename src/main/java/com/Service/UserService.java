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









    //根据用户名查询用户密码
    public String getUserPassword(String userName) {
        return userMapper.getUserPassword(userName);
    }

    //根据用户名查询用户id
    public Integer getUserId(String userName) {
        return userMapper.getUserId(userName);
    }
    //根据用户名查询用户名，用于重名检验
    public String getUserName(String userName) {
        return userMapper.getUserName(userName);
    }

    //根据邮箱查询邮箱，用于重复邮箱检验
    public String getUserEmail(String userEmail) {
        return userMapper.getUserEmail(userEmail);
    }

    //根据手机号查询手机号，用于重复手机号检验
    public String getUserPhone(String userPhone) {
        return userMapper.getUserPhone(userPhone);
    }

    //注册添加用户
    public Integer addUser(String userName, String userPassword, String userEmail, String userPhone) {
        return userMapper.addUser(userName, userPassword, userEmail, userPhone);
    }

    //获得所有用户的信息
    public User[] getUsers(){
        return userMapper.getUsers();
    }

}
