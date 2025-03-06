package com.lim.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lim.exception.ReferentialIntegrityException;
import com.lim.mapper.CategoryMapper;
import com.lim.mapper.DishMapper;
import com.lim.mapper.SetMealMapper;
import com.lim.pojo.Category;
import com.lim.pojo.Emp;
import com.lim.pojo.PagingQueryResult;
import com.lim.service.CategoryService;
import com.lim.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    public void addCategory(Category category,Long userId) {
        category.setCreateTime(LocalDateTime.now());
        category.setCreateUser(userId);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(userId);

        categoryMapper.addCategory(category);

    }

    @Override
    public PagingQueryResult<Category> pagingQuery(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);

        Page<Category> queryResult = (Page<Category>) categoryMapper.pagingQuery();

        return new PagingQueryResult<>(queryResult.getTotal(), queryResult.getResult());
    }

    @Override
    public void delCategoryById(Long id) {
        //还需检查当前菜品/套餐分类中的参照完整性 -- category_id ref category.id
        if(dishMapper.countByCategoryId(id) != 0){
            throw new ReferentialIntegrityException("该分类还有菜品使用，暂时不能删除~~");
        }
        if(setMealMapper.countByCategoryId(id) != 0){
            throw new ReferentialIntegrityException("该分类还有套餐使用，暂时不能删除~~");
        }

        //没有外键约束才执行删除
        categoryMapper.delCategoryById(id);
    }

    @Override
    public void updateCategoryById(Category category,Long userId) {
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateUser(userId);

        categoryMapper.updateCategoryById(category);
    }

    @Override
    public List<Category> selectByType(Integer type) {
        return  categoryMapper.selectByType(type);
    }

    @Override
    public List<Category> selectAll() {
        return categoryMapper.selectAll();
    }
}
