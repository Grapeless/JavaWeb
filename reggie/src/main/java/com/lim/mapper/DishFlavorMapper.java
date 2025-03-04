package com.lim.mapper;

import com.lim.pojo.Dish;
import com.lim.pojo.DishFlavor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    @Insert("insert into dish_flavor(dish_id,name, value,create_time, update_time, create_user, update_user) " +
            "VALUES (#{dishId},#{name},#{value},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addDishFlavor(DishFlavor dishFlavor);

    @Select("select * from dish_flavor where dish_id = #{dishId}")
    List<DishFlavor> selectByDishId(Long dishId);

    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteDishFlavor(Long dishId);
}
