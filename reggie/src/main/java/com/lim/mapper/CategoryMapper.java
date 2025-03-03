package com.lim.mapper;

import com.lim.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(name,type,sort,update_user,update_time,create_time,create_user)" +
            " values (#{name},#{type},#{sort},#{updateUser},#{updateTime},#{createTime},#{createUser})")
    void addCategory(Category category);

    @Select("select * from category")
    List<Category> pagingQuery();

    @Delete("delete from category where id = #{id}")
    void delCategoryById(Long id);

    @Update("update category set name = #{name},sort = #{sort},update_time = #{updateTime}," +
            "update_user = #{updateUser} where id = #{id}")
    void updateCategoryById(Category category);

    @Select("select * from category where type = #{type} order by sort,update_time desc")
    List<Category> selectByType(Integer type);
}
