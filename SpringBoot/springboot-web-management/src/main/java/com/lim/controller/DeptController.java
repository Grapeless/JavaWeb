package com.lim.controller;

import com.lim.anno.Log;
import com.lim.pojo.Dept;
import com.lim.pojo.Result;
import com.lim.service.DeptService;
import com.lim.service.impl.DeptServiceImpl;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    //public static Logger Log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");

        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除数据:{}", id);

        deptService.delete(id);

        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("添加部门信息:{}",dept);

        deptService.insert(dept);

        return Result.success();
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门信息:{}",dept);

        deptService.update(dept);

        return Result.success();
    }


}
