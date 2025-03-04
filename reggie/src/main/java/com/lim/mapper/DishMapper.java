package com.lim.mapper;

import com.lim.pojo.Dish;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("select count(*) from dish where category_id = #{id}")
    Integer countByCategoryId(Long id);

    @Options(keyProperty = "id",useGeneratedKeys = true)
    @Insert("insert into dish(name, category_id, price, code, image, description, create_time, update_time, create_user, update_user) values " +
            "(#{name},#{categoryId},#{price},'' , #{image}, #{description},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    void addDish(Dish dish);

    List<Dish> pagingQuery(String name);

    @Select("select * from dish where id = #{id}")
    Dish selectById(Long id);

    void updateDish(Dish dish);

    @Delete("delete from dish where id = #{id}")
    void deleteDishById(Long id);

    @Update("update dish set status = 0 where id = #{id}")
    void switchOffDishStatusById(Long id);

    @Update("update dish set status = 1 where id = #{id}")
    void switchOnDishStatusById(Long id);

    @Select("select * from dish where status = 1 and category_id = #{categoryId} ")
    List<Dish> selectByCategoryId(Long categoryId);

    @Select("select status from dish where id = #{id} ")
    Integer selectStatusById(Long id);
}
