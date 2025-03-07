package com.lim.service.impl;

import com.lim.mapper.ShoppingCartMapper;
import com.lim.pojo.ShoppingCart;
import com.lim.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<ShoppingCart> add(ShoppingCart shoppingCart, Long userId) {
        shoppingCart.setUserId(userId);
        shoppingCart.setCreateTime(LocalDateTime.now());

        //查询当前菜品/套餐是否已经在购物车中,分别查询shopping_cart中是否已经包含dish_id，setMeal_id
        ShoppingCart selectResult;
        if (shoppingCart.getSetmealId() != null) {
            selectResult = shoppingCartMapper.selectBySetMealIdAndUserId(userId, shoppingCart.getSetmealId());
        } else {
            selectResult = shoppingCartMapper.selectByDishIdAndUserId(userId, shoppingCart.getDishId());
        }

        //将对应菜品/套餐的数量加一
        if (selectResult != null) {
            shoppingCartMapper.add(selectResult);
        } else {
            //都不存在
            shoppingCartMapper.insert(shoppingCart);
        }
        return shoppingCartMapper.selectByUserId(userId);
    }

    @Override
    public List<ShoppingCart> sub(ShoppingCart shoppingCart, Long userId) {
        if (shoppingCart.getSetmealId() != null){
            //查询该套餐已有数量,==1则删除,>1则减一
            ShoppingCart selectResult = shoppingCartMapper.selectBySetMealIdAndUserId(userId, shoppingCart.getSetmealId());
            if (selectResult.getNumber() == 1) {
                shoppingCartMapper.deleteBySetMealId(shoppingCart.getSetmealId());
            } else {
                shoppingCartMapper.sub(selectResult);
            }
        }else {
            ShoppingCart selectResult  = shoppingCartMapper.selectByDishIdAndUserId(userId, shoppingCart.getDishId());
            if (selectResult.getNumber() == 1) {
                shoppingCartMapper.deleteByDishId(shoppingCart.getDishId());
            } else {
                shoppingCartMapper.sub(selectResult);
            }
        }
        return shoppingCartMapper.selectByUserId(userId);
    }

    @Override
    public List<ShoppingCart> selectById(Long userId) {
        return shoppingCartMapper.selectByUserId(userId);
    }

    @Override
    public void deleteById(Long userId) {
        shoppingCartMapper.deleteByUserId(userId);
    }
}
