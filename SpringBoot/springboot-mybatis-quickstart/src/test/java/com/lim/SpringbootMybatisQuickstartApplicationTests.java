package com.lim;

import com.lim.mapper.UserMapper;
import com.lim.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserList() {
        List<User> userList = userMapper.userList();
        userList.forEach(System.out::println);

        /*userList.forEach(user -> {
            user.equals("sd");
        });*/
    }

}
