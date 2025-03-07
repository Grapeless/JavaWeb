package com.lim.mapper;

import com.lim.pojo.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    @Select("select * from shopping_cart where user_id = #{userId} and setmeal_id = #{setMealId}")
    ShoppingCart selectBySetMealIdAndUserId(@Param("userId") Long userId,@Param("setMealId") Long setMealId);

    @Select("select * from shopping_cart where user_id = #{userId} and dish_id = #{dishId}")
    ShoppingCart selectByDishIdAndUserId(@Param("userId") Long userId,@Param("dishId") Long dishId);

    @Insert("insert into shopping_cart(name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) " +
            "values (#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},1,#{amount},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Update("update shopping_cart set number = #{number} + 1 where id = #{id} ")
    void add(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart where user_id = #{userId} ")
    List<ShoppingCart> selectByUserId(Long userId);

    @Delete("delete from shopping_cart where user_id = #{userId} ")
    void deleteByUserId(Long userId);

    @Delete("delete from shopping_cart where setmeal_id = #{setMealId} ")
    void deleteBySetMealId(Long setMealId);

    @Delete("delete from shopping_cart where dish_id = #{deahId} ")
    void deleteByDishId(Long dishId);

    @Update("update shopping_cart set number = #{number} - 1 where id = #{id} ")
    void sub(ShoppingCart shoppingCart);
}
