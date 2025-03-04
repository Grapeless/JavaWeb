package com.lim.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lim.pojo.Dish;
import com.lim.pojo.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishCategoryDTO {

    //展平Dish对象
    //避免前端的data.records = {{dish.id,dish.type...},categoryName} != {dish.id,dish.type...categoryName}
    @JsonUnwrapped
    private Dish dish;

    //category.name
    private String categoryName;

    private Integer copies;

    public DishCategoryDTO(Dish dish,String categoryName){
        this.dish = dish;
        this.categoryName = categoryName;
    }


}
