package com.lim.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.lim.exception.DataMissingException;
import com.lim.mapper.*;
import com.lim.pojo.*;
import com.lim.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final AddressBookMapper addressBookMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserMapper userMapper;
    private final OrderDetailMapper orderDetailMapper;

    public OrderServiceImpl(OrderMapper orderMapper, AddressBookMapper addressBookMapper, ShoppingCartMapper shoppingCartMapper, UserMapper userMapper, OrderDetailMapper orderDetailMapper) {
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.addressBookMapper = addressBookMapper;
        this.shoppingCartMapper = shoppingCartMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void submitOrder(Orders orders, Long userId) {
        AddressBook addressBook = addressBookMapper.selectById(orders.getAddressBookId());
        if (addressBook == null) {
            throw new DataMissingException("地址不能为空！");
        }
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByUserId(userId);
        if (shoppingCartList.isEmpty()) {
            throw new DataMissingException("购物车空空如也~~");
        }
        User user = userMapper.selectById(userId);
        Long orderId = IdWorker.getId();

        AtomicInteger amount = new AtomicInteger(0);
        //设置order_detail
        shoppingCartList.forEach(shoppingCartItem -> {
            OrderDetail orderDetail = new OrderDetail();

            //id--自增
            //name
            orderDetail.setName(shoppingCartItem.getName());

            //image
            orderDetail.setImage(shoppingCartItem.getImage());

            //orderId
            orderDetail.setOrderId(orderId);

            //dishId
            orderDetail.setDishId(shoppingCartItem.getDishId());

            //setMealId
            orderDetail.setSetmealId(shoppingCartItem.getSetmealId());

            //dishFlavor
            orderDetail.setDishFlavor(shoppingCartItem.getDishFlavor());

            //number
            orderDetail.setNumber(shoppingCartItem.getNumber());

            //account
            orderDetail.setAmount(shoppingCartItem.getAmount());

            amount.addAndGet(shoppingCartItem.getAmount().multiply(new BigDecimal(shoppingCartItem.getNumber())).intValue());

            orderDetailMapper.insert(orderDetail);
        });

        //设置order
        //id--IdWork生成
        orders.setId(orderId);

        //number--字符串化id
        orders.setNumber(orderId.toString());

        //status
        orders.setStatus(2);

        //userId
        orders.setUserId(userId);

        //addressBookId--前端传递

        //orderTime
        orders.setOrderTime(LocalDateTime.now());

        //checkoutTime
        orders.setCheckoutTime(LocalDateTime.now());

        //payMethod--前端传递

        //amount--计算购物车中物品得
        orders.setAmount(new BigDecimal(amount.get()));

        //remark--前端传递

        //phone
        orders.setPhone(addressBook.getPhone());

        //address
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));

        //userName
        orders.setUserName(user.getName());

        //consignee
        orders.setConsignee(addressBook.getConsignee());

        orderMapper.insert(orders);


    }
}
