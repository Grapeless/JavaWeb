package com.lim.service;


import com.lim.dto.DishCategoryDTO;
import com.lim.pojo.Dish;
import com.lim.pojo.PagingQueryResult;

import java.util.List;

public interface DishService {
    void addDish(Dish dish, Long userId);

    PagingQueryResult<DishCategoryDTO> pagingQuery(Integer page, Integer pageSize, String name);

    Dish selectById(Long id);

    void updateDish(Dish dish,Long userId);

    Boolean deleteDish(Long[] ids);

    void switchOffDishStatus(Long[] ids);

    void switchOnDishStatus(Long[] ids);

    List<Dish> selectByCategoryId(Long categoryId);
}
