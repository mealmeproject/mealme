package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CartDto {
    private long cartNumber;
    private long userNumber;
    private long productNumber;
    private int cartCount;
}
