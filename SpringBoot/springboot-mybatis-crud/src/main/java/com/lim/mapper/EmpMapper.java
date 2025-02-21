package com.lim.mapper;

import com.lim.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    //删
    @Delete("delete from emp where id = #{id}")
    void deleteByID(Integer id);

    //增
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Emp emp);

    //改
    void update(Emp emp);

    //据主键查询
    @Select("select * from emp where id = #{id}")
    Emp selectByID(Integer id);

    //其他查询
    List<Emp> complexSelect(String name, Short gender, LocalDate begin, LocalDate end);

    //批量删除
    void deleteByIDs(List<Integer> idList);

    //集合方法（例如批量添加元素）
}
