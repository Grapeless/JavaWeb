package com.lim.controller;

import com.lim.pojo.Emp;
import com.lim.pojo.Result;
import com.lim.service.EmpService;
import com.lim.service.impl.EmpServiceA;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    /*@Qualifier("empServiceB")
    @Autowired*/

    @Resource(name = "empServiceA")
    private EmpService empService;
    @RequestMapping("/listEmp")
    public Result getList() {
        List<Emp> empList = empService.handleEmpList();
        return Result.success(empList);
    }
}
