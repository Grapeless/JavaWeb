package com.lim.service.impl;

import com.lim.exception.DataMissingException;
import com.lim.mapper.AddressBookMapper;
import com.lim.mapper.OrderMapper;
import com.lim.mapper.ShoppingCartMapper;
import com.lim.mapper.UserMapper;
import com.lim.pojo.AddressBook;
import com.lim.pojo.Orders;
import com.lim.pojo.ShoppingCart;
import com.lim.pojo.User;
import com.lim.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final AddressBookMapper addressBookMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final UserMapper userMapper;

    public OrderServiceImpl(OrderMapper orderMapper, AddressBookMapper addressBookMapper, ShoppingCartMapper shoppingCartMapper, UserMapper userMapper) {
        this.orderMapper = orderMapper;
        this.addressBookMapper = addressBookMapper;
        this.shoppingCartMapper = shoppingCartMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void submitOrder(Orders orders, Long userId) {
        AddressBook addressBook = addressBookMapper.selectById(orders.getAddressBookId());
        if (addressBook == null){
            throw new DataMissingException("地址不能为空！");
        }
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.selectByUserId(userId);
        if (shoppingCartList.isEmpty()){
            throw new DataMissingException("购物车空空如也~~");
        }
        User user = userMapper.selectById(userId);
    }
}
