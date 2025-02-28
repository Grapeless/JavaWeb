package com.lim.mapper;

import com.lim.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select * from dept")
    List<Dept> list();

    @Delete("delete from dept where id = #{id}")
    void delete(Integer id);

    @Insert("insert into dept(name, create_time, update_time)" +
            " VALUES (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Update("update dept set name = #{name} , update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
