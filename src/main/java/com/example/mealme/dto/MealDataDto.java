package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MealDataDto {
    private long foodNumber;
    private long boardNumber;
    private String foodName;
    private String foodWeight;
    private String foodServing;
    private String foodKcal;
    private String foodCarbohydrate;
    private String foodProtein;
    private String foodFat;
    private String foodSugars;
    private String foodSodium;
    private String foodCholesterol;
    private String foodFattyAcid;
    private String foodTransFat;
}
