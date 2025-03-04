package com.lim.mapper;

import com.lim.pojo.SetMeal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SetMealMapper {
    @Select("select count(*) from setmeal where category_id = #{id}")
    int countByCategoryId(Long id);

    void addSetMeal(SetMeal setMeal);

    List<SetMeal> pagingQuery(String name);

    @Select("select status from setmeal where id = #{id} ")
    Integer selectStatusById(Long id);

    @Delete("delete from setmeal where id = #{id} ")
    void deleteSetMealById(Long id);

    @Update("update setmeal set status = 0 where id = #{id} ")
    void switchOffSetMealStatusById(Long id);

    @Update("update setmeal set status = 1 where id = #{id} ")
    void switchOnSetMealStatusById(Long id);
}
