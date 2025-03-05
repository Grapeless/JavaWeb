package com.lim.service.impl;

import com.lim.mapper.UserMapper;
import com.lim.pojo.User;
import com.lim.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectUserByPhone(String phone) {
        return userMapper.selectUserByPhone(phone);
    }

    @Override
    public User insertUser(String code, String phone) {
        User user = new User();
        user.setPhone(phone);
        userMapper.insertUser(user);
        return user;
    }
}
