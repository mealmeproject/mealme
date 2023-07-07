package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class OrderDto {
    private long orderNumber;
    private long userNumber;
    private long productNumber;
    private long orderConditionCode;
    private long shippingAddressNumber;
    private int orderCount;
    private String orderDate;

}
