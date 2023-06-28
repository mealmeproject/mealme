package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FoodVo {
//    TBL_BOARD.BOARD_NUMBER, TBL_BOARD.USER_NUMBER,TBL_BOARD.MEAL_TIME,
//    TBL_FOOD.FOOD_NUMBER, TBL_FOOD.FOOD_NAME, TBL_FOOD.FOOD_WEIGHT, TBL_FOOD.FOOD_SERVING,
//    TBL_FOOD.FOOD_KCAL, TBL_FOOD.FOOD_CARBOHYDRATE, TBL_FOOD.FOOD_PROTEIN, TBL_FOOD.FOOD_FAT, TBL_FOOD.FOOD_SUGARS,
//    TBL_FOOD.FOOD_SODIUM, TBL_FOOD.FOOD_CHOLESTEROL, TBL_FOOD.FOOD_FATTY_ACID, TBL_FOOD.FOOD_TRANS_FAT,
//    TBL_MEAL_CODE.MEAL_CODE_NUMBER, TBL_MEAL_CODE.MEAL_NAME,
//    TBL_BOARD_FILE.FILE_NUMBER, TBL_BOARD_FILE.FILE_NAME, TBL_BOARD_FILE.FILE_UPLOAD_PATH, TBL_BOARD_FILE.FILE_UUID
    private long boardNumber;
    private long userNumber;
    private String mealTime;

    private long foodNumber;


    private String foodName;
    private long foodWeight;
    private long foodServing;
    private long foodKcal;
    private long foodCarbohydrate;
    private long foodProtein;
    private long foodFat;
    private long foodSugars;
    private long foodSodium;
    private long foodCholesterol;
    private long foodFattyAcid;
    private long foodTransFat;

    private long mealCodeNumber;
    private String mealName;

    private String fileName;
    private String fileUploadPath;
    private String fileUuid;


}
