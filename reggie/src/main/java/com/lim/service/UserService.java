package com.lim.service;


import com.lim.pojo.User;

public interface UserService {
    User selectUserByPhone(String phone);

    User insertUser(String code, String phone);
}
