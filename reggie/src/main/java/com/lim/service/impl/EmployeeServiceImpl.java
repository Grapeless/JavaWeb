package com.lim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lim.mapper.EmployeeMapper;
import com.lim.pojo.Employee;
import com.lim.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
