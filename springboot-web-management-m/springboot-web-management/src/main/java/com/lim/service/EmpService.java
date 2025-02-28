package com.lim.service;

import com.lim.pojo.Emp;
import com.lim.pojo.PageBean;
import com.lim.pojo.SearchConditionBean;

import java.util.List;

public interface EmpService {
    PageBean page(SearchConditionBean scb);

    void delete(List<Integer> ids);

    void insert(Emp emp);

    Emp getEmpById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
