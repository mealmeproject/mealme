package com.example.mealme.util;

import com.example.mealme.vo.FoodVo;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class CalcNutrientUtil {
    private int kcalSum = 0;
    private int carbohydrateSum = 0;
    private int proteinSum = 0;
    private int fatSum = 0;
    private int sugarsSum = 0;
    private int sodiumSum = 0;
    private int cholesterolSum = 0;
    private int fattyAcidSum = 0;
    private int transFatSum = 0;

    public void calc(List<FoodVo> foods){
        for(FoodVo food : foods){
            this.kcalSum += food.getFoodKcal();
            this.carbohydrateSum += food.getFoodCarbohydrate();
            this.proteinSum += food.getFoodProtein();
            this.fatSum += food.getFoodFat();
            this.sugarsSum += food.getFoodSugars();
            this.sodiumSum += food.getFoodSodium();
            this.cholesterolSum += food.getFoodCholesterol();
            this.fattyAcidSum += food.getFoodFattyAcid();
            this.transFatSum += food.getFoodTransFat();
        }
    }

}
