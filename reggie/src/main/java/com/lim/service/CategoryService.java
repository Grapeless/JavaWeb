package com.lim.service;

import com.lim.pojo.Category;
import com.lim.pojo.PagingQueryResult;

public interface CategoryService {
    void addCategory(Category category,Long userId);

    PagingQueryResult<Category> pagingQuery(Integer page, Integer pageSize);

    void delCategoryById(Long id);

    void updateCategoryById(Category category,Long userId);
}
