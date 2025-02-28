package com.lim.controller;

import com.lim.pojo.Emp;
import com.lim.pojo.Result;
import com.lim.service.EmpService;
import com.lim.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("登录信息:{},{}", emp.getUsername(), emp.getPassword());

        Emp e = empService.login(emp);

        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            claims.put("password", e.getPassword());

            String jwt = JwtUtils.generateJwt(claims);

            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }
}
