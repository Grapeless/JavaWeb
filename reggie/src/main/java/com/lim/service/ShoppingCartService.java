package com.lim.service;

import com.lim.pojo.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> add(ShoppingCart shoppingCart, Long userId);

    List<ShoppingCart> selectById(Long userId);

    void deleteById(Long userId);

    List<ShoppingCart> sub(ShoppingCart shoppingCart, Long userId);
}
