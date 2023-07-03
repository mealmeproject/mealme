package com.example.mealme.util;

import com.example.mealme.vo.DayMealVo;
import com.example.mealme.vo.MealVo;

import java.util.*;

public class MealUtil {

    public static Map<String, List<DayMealVo>> getMealList(List<MealVo> mealVoList) {
        Map<String, List<DayMealVo>> map = new HashMap<>();

        for (MealVo meal : mealVoList) {
            String mealDate = meal.getMealDate();

            // mealDate가 이미 맵에 존재하는지 확인합니다.
            if (map.containsKey(mealDate)) {
                List<DayMealVo> dayMealVoList = map.get(mealDate);
                DayMealVo dayMealVo = null;

                // dayMealVoList에서 DayMealVo 있는 지 확인
                for (DayMealVo existingDayMealVo : dayMealVoList) {
                    // 날짜를 기준으로 적합한 DayMealVo를 식별합니다.
                    if (existingDayMealVo.getDate().equals(mealDate)) {
                        dayMealVo = existingDayMealVo;
                        break;
                    }
                }

                // 해당하는 DayMealVo가 있으면 업데이트(칼로리와 식단)
                if (dayMealVo != null) {
                    dayMealVo.setDayTotalKcal(dayMealVo.getDayTotalKcal() + meal.getMealTotalKcal());
                    dayMealVo.getDayMealList().add(meal);
                } else {
                    // 해당하는 DayMealVo가 없으면 새로운 DayMealVo를 만들고 추가함
                    DayMealVo newDayMealVo = new DayMealVo();
                    newDayMealVo.setDate(mealDate);
                    newDayMealVo.setDayTotalKcal(meal.getMealTotalKcal());
                    newDayMealVo.setDayMealList(new ArrayList<>(Collections.singletonList(meal)));
                    dayMealVoList.add(newDayMealVo);
                }
            } else {
                // mealDate가 맵에 없으면 새 DayMealVo를 생성하고 맵에 추가핟나. 
                List<DayMealVo> dayMealVoList = new ArrayList<>();
                DayMealVo dayMealVo = new DayMealVo();
                dayMealVo.setDate(mealDate);
                dayMealVo.setDayTotalKcal(meal.getMealTotalKcal());
                dayMealVo.setDayMealList(new ArrayList<>(Collections.singletonList(meal)));

                dayMealVoList.add(dayMealVo);
                map.put(mealDate, dayMealVoList);
            }
        }
        System.out.println(map);
        return map;
    }

}
