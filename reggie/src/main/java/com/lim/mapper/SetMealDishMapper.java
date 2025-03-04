package com.lim.mapper;

import com.lim.pojo.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SetMealDishMapper {
    void addSetMealDish(SetmealDish setmealDish);

    @Delete("delete from setmeal_dish where id = #{setMealId}  ")
    void deleteSetMealDishBySetMealId(Long setMealId);

}
