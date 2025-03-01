package com.lim.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lim.mapper.EmpMapper;
import com.lim.pojo.Emp;
import com.lim.pojo.PagingQueryResult;
import com.lim.service.EmpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;


    @Override
    public Emp login(Emp emp) {
        //数据库中密码已加密，需要对前端传下的raw密码加密后查询
        emp.setPassword(DigestUtils.md5DigestAsHex(emp.getPassword().getBytes()));
        return empMapper.selectByUsernameAndPassword(emp);
    }

    //增加员工
    @Override
    public void addEmp(Emp emp,Long userId) {
        emp.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateUser(userId);
        emp.setUpdateUser(userId);
        empMapper.insert(emp);
    }

    //分页查询
    @Override
    public PagingQueryResult pagingQuery(Integer page, Integer pageSize, String name) {
        log.info("page:{},pageSize:{}", page, pageSize);
        //设置分页查询参数
        PageHelper.startPage(page, pageSize);

        //获得分页查询数据
        Page<Emp> queryResult = (Page<Emp>) empMapper.pagingQuery(name);

        return new PagingQueryResult(queryResult.getTotal(), queryResult.getResult());
    }

    //修改员工状态
    @Override
    public void updateById(Emp emp,Long userId) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setUpdateUser(userId);

        empMapper.updateById(emp);
    }

    @Override
    public Emp selectById(Long id) {
        return empMapper.selectById(id);
    }
}
