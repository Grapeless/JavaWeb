package com.lim.mapper;

import com.lim.pojo.Emp;
import com.lim.pojo.SearchConditionBean;
import org.apache.ibatis.annotations.Mapper;

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
}
