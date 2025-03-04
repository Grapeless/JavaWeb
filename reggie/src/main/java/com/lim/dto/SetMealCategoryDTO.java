package com.lim.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.lim.pojo.SetMeal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetMealCategoryDTO {
    @JsonUnwrapped
    private SetMeal setMeal;

    private String categoryName;


}
