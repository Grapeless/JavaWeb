package com.lim.service;

import com.lim.dto.SetMealCategoryDTO;
import com.lim.pojo.PagingQueryResult;
import com.lim.pojo.SetMeal;

public interface SetMealService {
    void addSetMeal(SetMeal setMeal,Long userId);

    PagingQueryResult<SetMealCategoryDTO> pagingQuery(Integer page, Integer pageSize, String name);

    boolean deleteSetMeal(Long[] ids);

    void switchOffSetMealStatus(Long[] ids);

    void switchOnSetMealStatus(Long[] ids);
}
