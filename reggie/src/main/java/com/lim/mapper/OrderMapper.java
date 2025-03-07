package com.lim.mapper;

import com.lim.pojo.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    @Insert("insert into orders(number, status, user_id, address_book_id, order_time, checkout_time, pay_method, amount, remark, phone, address, user_name, consignee) VALUES " +
            "(#{number},#{status},#{userId},#{addressBookId},#{orderTime},#{checkoutTime},#{payMethod},#{amount},#{remark},#{phone},#{address},#{userName},#{consignee})")
    void insert(Orders orders);
}
