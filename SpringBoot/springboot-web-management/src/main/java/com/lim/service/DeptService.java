package com.lim.service;

import com.lim.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void delete(Integer id);

    void insert(Dept dept);

    void update(Dept dept);
}
