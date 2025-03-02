package com.lim.controller;

import com.lim.pojo.Category;
import com.lim.pojo.PagingQueryResult;
import com.lim.pojo.Result;
import com.lim.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //添加菜品分类/套餐信息
    @PostMapping()
    public Result addCategory(@RequestBody Category category, HttpServletRequest req) {
        log.info("新增菜品/套餐分类信息:{}", category);
        //userId -- 当前登录用户的id
        Long userId = (Long) req.getSession().getAttribute("id");

        categoryService.addCategory(category, userId);

        return Result.success();
    }

    //分页查询
    @GetMapping("/page")
    public Result pagingQuery(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PagingQueryResult<Category> pagingQueryResult = categoryService.pagingQuery(page, pageSize);
        return Result.success(pagingQueryResult);
    }

    //删除分类
    @DeleteMapping()
    public Result delCategoryById(Long ids){
        log.info("将要删除的分类id:{}",ids);
        categoryService.delCategoryById(ids);
        return Result.success();
    }

    //修改分类
    @PutMapping()
    public Result updateCategory(@RequestBody Category category,HttpServletRequest req){
        log.info("将要修改的分类信息:{}",category);
        Long userId = (Long) req.getSession().getAttribute("id");
        categoryService.updateCategoryById(category,userId);
        return Result.success();
    }
}
