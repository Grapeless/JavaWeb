package com.lim.controller;

import com.lim.dto.SetMealCategoryDTO;
import com.lim.pojo.PagingQueryResult;
import com.lim.pojo.Result;
import com.lim.pojo.SetMeal;
import com.lim.service.SetMealService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetMealController {
    @Autowired
    private SetMealService setMealService;

    //增加套餐
    @PostMapping()
    public Result addSetMeal(@RequestBody SetMeal setMeal, HttpServletRequest req) {
        log.info("即将新增的菜品信息:{}", setMeal);
        Long userId = (Long) req.getSession().getAttribute("id");
        setMealService.addSetMeal(setMeal, userId);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result pagingQuery(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              String name) {
        PagingQueryResult<SetMealCategoryDTO> pagingQueryResult = setMealService.pagingQuery(page, pageSize, name);
        return Result.success(pagingQueryResult);
    }

    //删除菜品(一个或多个)
    @DeleteMapping()
    public Result deleteSetMeal(Long[] ids){
        if(setMealService.deleteSetMeal(ids)){
            return Result.success();
        }else {
            return Result.error("存在套餐还在售卖，暂时无法删除");
        }
    }

    //停售菜品(一个或多个)
    @PostMapping("/status/0")
    public Result switchOffSetMealStatus(Long[] ids){
        setMealService.switchOffSetMealStatus(ids);
        return Result.success();
    }

    //起售菜品(一个或多个)
    @PostMapping("/status/1")
    public Result switchOnSetMealStatus(Long[] ids){
        setMealService.switchOnSetMealStatus(ids);
        return Result.success();
    }
}
