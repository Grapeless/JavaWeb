package com.lim.mapper;

import com.lim.pojo.Dish;
import com.lim.pojo.DishFlavor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishFlavorMapper {
    @Insert("insert into dish_flavor(dish_id,name, value,create_time, update_time, create_user, update_user) " +
            "VALUES (#{dishId},#{name},#{value},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addDishFlavor(DishFlavor dishFlavor);
}
