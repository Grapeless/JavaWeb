package com.lim.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lim.pojo.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
