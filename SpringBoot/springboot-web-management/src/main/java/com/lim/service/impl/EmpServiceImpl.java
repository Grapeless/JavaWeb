package com.lim.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lim.mapper.DeptMapper;
import com.lim.mapper.EmpMapper;
import com.lim.pojo.Emp;
import com.lim.pojo.PageBean;
import com.lim.pojo.SearchConditionBean;
import com.lim.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(SearchConditionBean scb) {
        Integer page = scb.getPage();
        Integer pageSize = scb.getPageSize();

        //PageHelper预设
        PageHelper.startPage(page,pageSize);
        //获取结果
        List<Emp> empList = empMapper.page(scb);
        Page<Emp> p = (Page<Emp>) empList;

        return new PageBean(p.getTotal(),p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);

    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }
}
