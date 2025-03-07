package com.lim.mapper;

import com.lim.pojo.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    @Insert("insert into order_detail(name, image, order_id, dish_id, setmeal_id, dish_flavor, amount) VALUES " +
            "(#{name},#{image},#{orderId},#{dishId},#{setmealId},#{dishFlavor},#{amount})")
    void insert(OrderDetail orderDetail);
}
