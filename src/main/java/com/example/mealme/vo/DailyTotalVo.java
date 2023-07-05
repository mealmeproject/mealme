package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class DailyTotalVo {
    private long userNumber;
//    날짜 일단 mealDate로 받는데 필요하면 mealTime으로 수정하기
    private String mealDate;
    private int totalFoodKcal;
    private int totalFoodCarbohydrate;
    private int totalFoodProtein;
    private int totalFoodFat;
    private int totalFoodSugars;
    private int totalFoodSodium;
    private int totalFoodCholesterol;
    private int totalFoodFattyAcid;
    private int totalFoodTransFat;
}
