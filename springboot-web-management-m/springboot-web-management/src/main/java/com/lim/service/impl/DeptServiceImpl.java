package com.lim.service.impl;

import com.lim.mapper.DeptMapper;
import com.lim.mapper.EmpMapper;
import com.lim.pojo.Dept;
import com.lim.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    //不指定rollbackFor的范围值时,则只有出现runtimeException时才执行rollback
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        //根据部门id删除部门，及其所拥有的员工信息
        deptMapper.delete(id);
        empMapper.deleteById(id);
    }

    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }
}
