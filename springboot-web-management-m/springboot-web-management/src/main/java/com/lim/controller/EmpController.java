package com.lim.controller;

import com.github.pagehelper.Page;
import com.lim.anno.Log;
import com.lim.pojo.Emp;
import com.lim.pojo.PageBean;
import com.lim.pojo.Result;
import com.lim.pojo.SearchConditionBean;
import com.lim.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result search(SearchConditionBean scb) {
        log.info("分页查询，起始页:{}，每页大小:{}，name:{},gender:{},begin:{},end:{}"
                , scb.getPage(), scb.getPageSize(), scb.getName(), scb.getGender(), scb.getBegin(), scb.getEnd());
        PageBean page = empService.page(scb);
        return Result.success(page);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {

        log.info("批量删除，删除id:{}", ids);

        empService.delete(ids);

        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp) {
        log.info("插入员工 {}", emp);

        empService.insert(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable Integer id) {

        log.info("所查询id:{}", id);

        Emp emp = empService.getEmpById(id);

        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){

        log.info("新数据：{}",emp);

        empService.update(emp);

        return Result.success();
    }
}
