package com.lim.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细
 */
@Data
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    //名称
    private String name;

    //订单id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long orderId;


    //菜品id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long dishId;


    //套餐id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long setmealId;


    //口味
    private String dishFlavor;


    //数量
    private Integer number;

    //金额
    private BigDecimal amount;

    //图片
    private String image;
}
