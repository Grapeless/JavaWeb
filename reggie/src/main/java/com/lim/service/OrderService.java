package com.lim.service;

import com.lim.pojo.Orders;

public interface OrderService {
    void submitOrder(Orders orders, Long userId);

}
