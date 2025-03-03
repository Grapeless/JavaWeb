package com.lim.mapper;

import com.lim.pojo.Dish;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

}
