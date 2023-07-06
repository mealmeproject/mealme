package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductCategory2Dto {
    private long categoryCode2;
    private String categoryName;
    private long categoryCode1;
}
