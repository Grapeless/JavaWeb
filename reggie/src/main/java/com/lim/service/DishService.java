package com.lim.service;


import com.lim.pojo.Dish;
import com.lim.pojo.PagingQueryResult;

public interface DishService {
    void addDish(Dish dish, Long userId);

    PagingQueryResult<Dish> pagingQuery(Integer page, Integer pageSize, String name);
}
