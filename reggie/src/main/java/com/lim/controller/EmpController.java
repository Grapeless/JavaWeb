package com.lim.controller;

import com.lim.pojo.Emp;
import com.lim.pojo.PagingQueryResult;
import com.lim.pojo.Result;
import com.lim.service.EmpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(HttpServletRequest req, @RequestBody Emp emp) {

        log.info("登录账户信息:{}", emp);
        log.info("URL:{}", req.getRequestURL());
        Emp e = empService.login(emp);

        //先判断用户是否存在
        if (e == null) {
            return Result.error("登录失败，请检查账号或密码");
        }

        //再判断账户禁用状态
        if (e.getStatus() == 0) {
            return Result.error("账户已经禁用");
        }

        //登录成功将用户id放入session
        req.getSession().setAttribute("id", e.getId());

        return Result.success(e);
    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest req) {
        req.getSession().removeAttribute("id");
        return Result.success();
    }

    //增加员工
    @PostMapping()
    public Result addEmp(@RequestBody Emp emp, HttpServletRequest req) {

        log.info("新增员工对象信息:{}", emp);
        Long userId = (Long) req.getSession().getAttribute("id");
        empService.addEmp(emp, userId);

        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result pagingQuery(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              String name) {
        PagingQueryResult pagingQueryResult = empService.pagingQuery(page, pageSize, name);
        return Result.success(pagingQueryResult);
    }

    //修改员工信息
    @PutMapping()
    public Result updateById(@RequestBody Emp emp, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("id");

        empService.updateById(emp,userId);
        return Result.success();
    }

    //查询员工信息回显
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Long id){
        Emp emp = empService.selectById(id);

        return Result.success(emp);
    }
}
