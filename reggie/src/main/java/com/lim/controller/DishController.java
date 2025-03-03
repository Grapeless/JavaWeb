package com.lim.controller;

import com.lim.pojo.Dish;
import com.lim.pojo.PagingQueryResult;
import com.lim.pojo.Result;
import com.lim.service.DishService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dish")
@RestController
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    //增加菜品
    @PostMapping()
    public Result addDish(@RequestBody Dish dish, HttpServletRequest req) {
        log.info("即将新增的菜品信息:{}", dish);

        Long userId = (Long) req.getSession().getAttribute("id");
        dishService.addDish(dish, userId);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result pagingQuery(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              String name) {
        PagingQueryResult<Dish> pagingQueryResult = dishService.pagingQuery(page,pageSize,name);

        return Result.success(pagingQueryResult);
    }
}
