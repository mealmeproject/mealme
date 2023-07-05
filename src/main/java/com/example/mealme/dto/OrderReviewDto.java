package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class OrderReviewDto {
    private long cartNumber;
    private long userNumber;
    private Long orderNumber;
    private long productNumber;
    private int cartCount;
}
