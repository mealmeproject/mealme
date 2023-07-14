package com.example.mealme.vo;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

@Data
@Transactional
public class DailyOrderVo {
    private long orderConditionCode;
    private long orderCount;
    private String orderDate;
    private long productNumber;
    private long productPrice;
    private long totalOrderCount;
    private long totalPrice;
    private long orderNumberCount;
}
