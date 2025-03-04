package com.lim.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lim.dto.SetMealCategoryDTO;
import com.lim.mapper.CategoryMapper;
import com.lim.mapper.SetMealDishMapper;
import com.lim.mapper.SetMealMapper;
import com.lim.pojo.PagingQueryResult;
import com.lim.pojo.SetMeal;
import com.lim.pojo.SetmealDish;
import com.lim.service.SetMealService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SetMealServiceImpl implements SetMealService {

    private final SetMealMapper setMealMapper;
    private final SetMealDishMapper setMealDishMapper;
    private final CategoryMapper categoryMapper;

    public SetMealServiceImpl(SetMealMapper setMealMapper, SetMealDishMapper setMealDishMapper, CategoryMapper categoryMapper) {
        this.setMealMapper = setMealMapper;
        this.setMealDishMapper = setMealDishMapper;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    @Override
    public void addSetMeal(SetMeal setMeal, Long userId) {
        setMeal.setUpdateTime(LocalDateTime.now());
        setMeal.setUpdateUser(userId);
        setMeal.setCreateTime(LocalDateTime.now());
        setMeal.setCreateUser(userId);
        setMealMapper.addSetMeal(setMeal);

        Long setMealId = setMeal.getId();
        for (SetmealDish setmealDish : setMeal.getSetmealDishes()) {
            setmealDish.setUpdateTime(LocalDateTime.now());
            setmealDish.setUpdateUser(userId);
            setmealDish.setCreateTime(LocalDateTime.now());
            setmealDish.setCreateUser(userId);
            setmealDish.setSetmealId(setMealId);
            setMealDishMapper.addSetMealDish(setmealDish);
        }
    }

    @Override
    public PagingQueryResult<SetMealCategoryDTO> pagingQuery(Integer page, Integer pageSize, String name) {
        //同DishServiceImpl
        PageHelper.startPage(page,pageSize);
        Page<SetMeal> queryResult = (Page<SetMeal>) setMealMapper.pagingQuery(name);

        List<SetMealCategoryDTO> setMealCategoryDTOList = new ArrayList<>();
        queryResult.forEach(setMeal -> {
            String categoryName = categoryMapper.selectNameById(setMeal.getCategoryId());
            setMealCategoryDTOList.add(new SetMealCategoryDTO(setMeal,categoryName));
        });

        return new PagingQueryResult<>(queryResult.getTotal(),setMealCategoryDTOList);
    }

    @Override
    public boolean deleteSetMeal(Long[] ids) {
        for (Long id : ids) {
            if(setMealMapper.selectStatusById(id) == 1){
                return false;
            }else {
                setMealMapper.deleteSetMealById(id);
                //删除关联的外键 setMeal_dish.setMeal_id
                setMealDishMapper.deleteSetMealDishBySetMealId(id);
            }
        }
        return true;
    }

    @Override
    public void switchOffSetMealStatus(Long[] ids) {
        for (Long id : ids) {
            setMealMapper.switchOffSetMealStatusById(id);
        }
    }

    @Override
    public void switchOnSetMealStatus(Long[] ids) {
        for (Long id : ids) {
            setMealMapper.switchOnSetMealStatusById(id);
        }
    }
}
