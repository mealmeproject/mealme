package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class OrderDto {
    private Long orderNumber;
    private Long userNumber;
    private Long productNumber;
    private Long orderConditionCode;
    private Long shippingAddressNumber;
    private int orderCount;
    private String orderDate;

}
