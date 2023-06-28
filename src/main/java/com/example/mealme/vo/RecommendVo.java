package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class RecommendVo {
    private int recommendKcal;
    private int recommendCarbohydrate;
    private int recommendProtein;
    private int recommendFat;
    private String userComment;

}
