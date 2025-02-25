package com.lim.mapper;

import com.lim.pojo.Emp;
import com.lim.pojo.SearchConditionBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {

    /*@Select("select count(*) from emp")
    Long count();

    @Select("select * from emp limit #{page},#{pageSize}")
    List<Emp> page(Integer page, Integer pageSize);*/

    List<Emp> page(SearchConditionBean scb);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getEmpById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getEmpByUsernameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id = #{id}")
    void deleteById(Integer id);
}
