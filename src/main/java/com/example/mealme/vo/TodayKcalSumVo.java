package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class TodayKcalSumVo {
    private long boardNumber;
    private long userNumber;
    private String mealTime;
    private int mealCodeNumber;
    private int breakfastTotal;
    private int lunchTotal;
    private int dinnerTotal;
    private int snackTotal;
}
