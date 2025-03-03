package com.lim.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lim.mapper.DishFlavorMapper;
import com.lim.mapper.DishMapper;
import com.lim.pojo.Dish;
import com.lim.pojo.DishFlavor;
import com.lim.pojo.PagingQueryResult;
import com.lim.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class DishServiceImpl implements DishService {
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Transactional
    @Override
    public void addDish(Dish dish, Long userId) {
        dish.setCreateTime(LocalDateTime.now());
        dish.setCreateUser(userId);
        dish.setUpdateTime(LocalDateTime.now());
        dish.setUpdateUser(userId);

        dishMapper.addDish(dish);
        log.info("最终添加的dish对象:{}",dish);

        //为每个口味关联dishId,同时加入dish_flavor
        Long dishId = dish.getId();
        for (DishFlavor flavor : dish.getFlavors()) {
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setCreateUser(userId);
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setUpdateUser(userId);

            flavor.setDishId(dishId);
            log.info("最终添加的dishFlavor对象:{}",flavor);
            dishFlavorMapper.addDishFlavor(flavor);
        }
    }

    @Override
    public PagingQueryResult<Dish> pagingQuery(Integer page, Integer pageSize, String name) {
        PageHelper.startPage(page,pageSize);

        Page<Dish> queryResult = (Page<Dish>) dishMapper.pagingQuery(name);

        return new PagingQueryResult<>(queryResult.getTotal(),queryResult.getResult());
    }
}
