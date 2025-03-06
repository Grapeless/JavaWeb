package com.lim.mapper;

import com.lim.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where phone = #{phone} ")
    User selectUserByPhone(String phone);

    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into user(phone) values ( #{phone} )")
    void insertUser(User user);

    @Select("select * from user where id = #{Id} ")
    User selectById(Long Id);
}
