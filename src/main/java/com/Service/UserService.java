package com.Service;

import com.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public String getUserPassword(String userName) {
        return userMapper.getUserPassword(userName);
    }

    public String getUserName(String userName) {
        return userMapper.getUserName(userName);
    }

    public Integer addUser(String userName, String userPassword) {
        return userMapper.addUser(userName, userPassword);
    }

}
