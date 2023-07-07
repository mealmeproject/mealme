package com.example.mealme.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderVo {
    private String orderDate;
    private long orderNumber;
    private String userName;
    private String productSeller;
    private String productName;
    private long productPrice;
    private long orderCount;
    private String conditionCodeName;
    private long orderConditionCode;
}
