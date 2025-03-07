package com.lim.controller;

import com.lim.pojo.Orders;
import com.lim.pojo.Result;
import com.lim.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/submit")
    public Result submitOrder(@RequestBody Orders orders, HttpServletRequest req){
        Long userId = (Long) req.getSession().getAttribute("userId");
        orderService.submitOrder(orders,userId);
        return Result.success();
        /// b1 commit
    }
}
