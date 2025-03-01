package com.lim.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lim.pojo.Emp;
import com.lim.pojo.PagingQueryResult;

public interface EmpService  {
    Emp login(Emp emp);

    void addEmp(Emp emp,Long userId);

    PagingQueryResult pagingQuery(Integer page, Integer pageSize, String name);

    void updateById(Emp emp,Long userId);

    Emp selectById(Long id);
}
