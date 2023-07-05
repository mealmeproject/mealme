package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class DayMealVo {
     private String date;
     private int dayTotalKcal;
     private List<MealVo> dayMealList;
}
