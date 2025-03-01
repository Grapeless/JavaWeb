package com.lim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lim.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Select("select * from employee where password = #{password} and username = #{username}")
    Emp selectByUsernameAndPassword(Emp emp);

    @Insert("insert into employee(name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user) VALUES " +
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void insert(Emp emp);

    List<Emp> pagingQuery(String name);

    void updateById(Emp emp);

    @Select("select * from employee where id = #{id}")
    Emp selectById(Long id);
}
