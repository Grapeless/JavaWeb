package com.lim.controller;

import com.lim.pojo.Result;
import com.lim.pojo.User;
import com.lim.service.UserService;
import com.lim.utils.AliSMSUtils;
import com.lim.utils.GenerateValidateCodeUtils;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sendMsg")
    public Result sendMsg(@RequestBody User user, HttpServletRequest req) throws Exception {
        //获取手机号
        String phone = user.getPhone();

        //判断手机号，不能为"","   ",null
        if (StringUtils.hasText(phone)) {
            //生成4位验证码
            String code = GenerateValidateCodeUtils.generateValidateCode4String(4);
            //给手机发送验证码
            AliSMSUtils.sendSM(phone, code);
            //将code存入session便于与用户实际填写的code校对
            req.getSession().setAttribute("code", code);

            return Result.success("发送成功");
        }
        return Result.error("请输入有效的手机号");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> map, HttpServletRequest req) {
        //用户输入的手机号及验证码
        String code = map.get("code");
        String phone = map.get("phone");

        //比较用户输入的验证码以及session中的验证码
        if (code.equals(req.getSession().getAttribute("code"))) {
            //校验成功
            User user = userService.selectUserByPhone(phone);
            if (user != null) {
                //手机号不存在，则插入新用户
                user = userService.insertUser(code, phone);
            }
            //否则直接登录成功
            req.getSession().setAttribute("userId", user);
            return Result.success(user);
        }
        //否则登录失败
        return Result.error("登录失败");
    }

}
