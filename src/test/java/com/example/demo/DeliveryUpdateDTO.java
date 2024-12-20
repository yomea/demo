package com.example.demo;

import java.io.Serializable;
import java.math.BigDecimal;

public class DeliveryUpdateDTO implements Serializable {

    private Long orderId;
    private Long orderItemId;
    private String factory;
    private String breed;
    private String material;
    private String spec;
    private String length;
    private Integer num;
    private BigDecimal weight;
    // 1：新增，2：修改，
    private int flag;

}
