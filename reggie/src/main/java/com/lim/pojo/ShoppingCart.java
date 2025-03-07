package com.lim.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 购物车
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    //名称
    private String name;

    //用户id
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

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

    private LocalDateTime createTime;
}
