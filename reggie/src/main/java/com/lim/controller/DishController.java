package com.lim.controller;

import com.lim.dto.DishCategoryDTO;
import com.lim.pojo.Dish;
import com.lim.pojo.PagingQueryResult;
import com.lim.pojo.Result;
import com.lim.service.DishService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dish")
@RestController
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;

    //增加菜品
    @PostMapping()
    public Result addDish(@RequestBody Dish dish, HttpServletRequest req) {
        //log.info("即将新增的菜品信息:{}", dish);

        Long userId = (Long) req.getSession().getAttribute("id");
        dishService.addDish(dish, userId);
        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result pagingQuery(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              String name) {
        PagingQueryResult<DishCategoryDTO> pagingQueryResult = dishService.pagingQuery(page, pageSize, name);

        //log.info("最后得到的查询结果:{}", pagingQueryResult.getRecords().get(0));
        return Result.success(pagingQueryResult);
    }

    //查询菜品信息然后回显
    @GetMapping("{id}")
    public Result selectById(@PathVariable Long id) {

        Dish dish = dishService.selectById(id);

        return Result.success(dish);
    }

    //根据菜系id查询
    @GetMapping("/list")
    public Result selectByCategoryId(Long categoryId){
        List<Dish> dishes = dishService.selectByCategoryId(categoryId);
        return Result.success(dishes);
    }


    //修改菜品信息
    @PutMapping()
    public Result updateDish(@RequestBody Dish dish, HttpServletRequest req) {
        Long userId = (Long) req.getSession().getAttribute("id");
        //log.info("即将修改的菜品信息:{}",dish);
        dishService.updateDish(dish, userId);
        return Result.success();
    }

    //删除菜品(一个或多个)
    @DeleteMapping()
    public Result deleteDish(Long[] ids) {
        if (dishService.deleteDish(ids) ){
            return Result.success();
        }else {
            return Result.error("存在商品还在售卖，暂时无法删除");
        }
    }

    //停售菜品(一个或多个)
    @PostMapping("/status/0")
    public Result updateDishStatus(Long[] ids) {
        dishService.switchOffDishStatus(ids);
        return Result.success();
    }

    //起售菜品(一个或多个)
    @PostMapping("/status/1")
    public Result switchOnDishStatus(Long[] ids) {
        dishService.switchOnDishStatus(ids);
        return Result.success();
    }
}
