package com.lim.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lim.dto.DishCategoryDTO;
import com.lim.mapper.CategoryMapper;
import com.lim.mapper.DishFlavorMapper;
import com.lim.mapper.DishMapper;
import com.lim.pojo.Dish;
import com.lim.pojo.DishFlavor;
import com.lim.pojo.PagingQueryResult;
import com.lim.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DishServiceImpl implements DishService {
    private final DishMapper dishMapper;
    private final DishFlavorMapper dishFlavorMapper;
    private final CategoryMapper categoryMapper;

    public DishServiceImpl(DishMapper dishMapper, DishFlavorMapper dishFlavorMapper, CategoryMapper categoryMapper) {
        this.dishMapper = dishMapper;
        this.dishFlavorMapper = dishFlavorMapper;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    @Override
    public void addDish(Dish dish, Long userId) {
        dish.setCreateTime(LocalDateTime.now());
        dish.setCreateUser(userId);
        dish.setUpdateTime(LocalDateTime.now());
        dish.setUpdateUser(userId);

        dishMapper.addDish(dish);
        //log.info("最终添加的dish对象:{}",dish);

        //为每个口味关联dishId,同时加入dish_flavor
        Long dishId = dish.getId();
        for (DishFlavor flavor : dish.getFlavors()) {
            flavor.setCreateTime(LocalDateTime.now());
            flavor.setCreateUser(userId);
            flavor.setUpdateTime(LocalDateTime.now());
            flavor.setUpdateUser(userId);

            flavor.setDishId(dishId);
            //log.info("最终添加的dishFlavor对象:{}",flavor);
            dishFlavorMapper.addDishFlavor(flavor);
        }
    }

    @Override
    public PagingQueryResult<DishCategoryDTO> pagingQuery(Integer page, Integer pageSize, String name) {
        //设置分页查询参数
        PageHelper.startPage(page, pageSize);
        //获得dish分页查询结果
        Page<Dish> queryResult = (Page<Dish>) dishMapper.pagingQuery(name);

        //声明一个DTO对象集合，用于封装分页查询的结果和categoryName
        List<DishCategoryDTO> dishCategoryDTOList = new ArrayList<>();

        //封装DTO集合
        queryResult.forEach(dish -> {
            //根据 dish.categoryID 查询 category.name
            String categoryName = categoryMapper.selectNameById(dish.getCategoryId());
            dishCategoryDTOList.add(new DishCategoryDTO(dish, categoryName));
        });

        //最后将结果的数量和封装了分页查询，categoryName的DTO对象集合作为PagingQueryResult返回
        return new PagingQueryResult<>(queryResult.getTotal(), dishCategoryDTOList);
    }

    @Override
    public Dish selectById(Long id) {
        //先查dish
        Dish dish = dishMapper.selectById(id);
        //再根据dish.id查dish_flavor
        dish.setFlavors(dishFlavorMapper.selectByDishId(id));

        return dish;
    }

    @Override
    public void updateDish(Dish dish, Long userId) {

        //先更新dish表相关字段
        dish.setUpdateUser(userId);
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.updateDish(dish);

        //再根据dish.id更新flavor表相关字段
        //由于dish.name在dish_flavor中不唯一,所以这个更改操作被我变成先删后增了
        dishFlavorMapper.deleteDishFlavor(dish.getId());
        dish.getFlavors().forEach(dishFlavor -> {
            dishFlavor.setUpdateTime(LocalDateTime.now());
            dishFlavor.setUpdateUser(userId);
            dishFlavor.setCreateTime(LocalDateTime.now());
            dishFlavor.setCreateUser(userId);
            dishFlavor.setDishId(dish.getId());
            dishFlavorMapper.addDishFlavor(dishFlavor);
        });
    }

    @Override
    public Boolean deleteDish(Long[] ids) {
        for (Long id : ids) {
            //如果有菜品处于"在售"状态则不允许删除
            if(dishMapper.selectStatusById(id) == 1 ){
                return false;
            }else {
                dishMapper.deleteDishById(id);
                //还要删除flavor中参照id的外键--dish_id
                dishFlavorMapper.deleteDishFlavor(id);
            }
        }
        return true;
    }

    @Override
    public void switchOffDishStatus(Long[] ids) {
        for (Long id : ids) {
            dishMapper.switchOffDishStatusById(id);
        }
    }

    @Override
    public void switchOnDishStatus(Long[] ids) {
        for (Long id : ids) {
            dishMapper.switchOnDishStatusById(id);
        }
    }

    @Override
    public List<Dish> selectByCategoryId(Long categoryId) {
        //只查询status=1的分类
         List<Dish> dishes = dishMapper.selectByCategoryId(categoryId);
         //附上菜品的口味信息
         dishes.forEach(dish -> {
             dish.setFlavors(dishFlavorMapper.selectByDishId(dish.getId()));
         });
         return dishes;
    }
}
