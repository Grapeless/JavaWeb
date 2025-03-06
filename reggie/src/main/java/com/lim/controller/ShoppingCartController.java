package com.lim.controller;

import com.lim.pojo.Result;
import com.lim.pojo.ShoppingCart;
import com.lim.service.ShoppingCartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    //增加菜品(dish)或套餐(setMeal)dish
    @PostMapping("/add")
    public Result add(@RequestBody ShoppingCart shoppingCart, HttpServletRequest req){
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<ShoppingCart> shoppingCartList = shoppingCartService.add(shoppingCart,userId);
        return Result.success(shoppingCartList);
    }

    //查看购物车
    @GetMapping("/list")
    public Result list(HttpServletRequest req){
        Long userId = (Long) req.getSession().getAttribute("userId");
        List<ShoppingCart> shoppingCartList = shoppingCartService.selectById(userId);
        return Result.success(shoppingCartList);
    }

    //清空购物车
    @DeleteMapping("clean")
    public Result clean(HttpServletRequest req){
        Long userId = (Long) req.getSession().getAttribute("userId");
        shoppingCartService.deleteById(userId);
        return Result.success();
    }
}
