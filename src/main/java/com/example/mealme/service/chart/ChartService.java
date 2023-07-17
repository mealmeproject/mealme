package com.example.mealme.service.chart;

import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.ChartMapper;
import com.example.mealme.util.CalcNutrientUtil;
import com.example.mealme.util.ChartUtil;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChartService {
    private final ChartMapper chartMapper;
    private final UserDto userDto;

    // 오늘페이지 내정보 뽑기
    public RecommendVo selectUserInfo(long userNumber){
        UserDto userDto = chartMapper.selectUserInfo(userNumber);
//        System.out.println(userDto);
        RecommendVo recommendVo = ChartUtil.calculate(userDto.getUserGender(), userDto.getUserHeight(), userDto.getUserWeight(), userDto.getUserBirth(), userDto.getUserComment());
        System.out.println("********************************************");
        System.out.println(recommendVo);
        return recommendVo;
    }

//    오늘 페이지 내가 먹은 음식
    public CalcNutrientUtil selectFoodInfo(long userNumber, String mealTime){
//        long userNumber = 1L;
//        DB의 값으로 뽑아보기 위해 임의로 설정
//        List<FoodVo> foodVo = chartMapper.selectFoodInfo(1L, "2023-06-21");
        List<FoodVo> foodVo = chartMapper.selectFoodInfo(userNumber, mealTime);
//        mealTime을 datepicker의 날짜로 받아야 함
//      controller에서 오늘 날짜로 값을 설정해놓음
        System.out.println("=========");
        System.out.println(foodVo);
        CalcNutrientUtil calcNutrientUtil = new CalcNutrientUtil();
        calcNutrientUtil.calc(foodVo);
        System.out.println("=======&&&&&&");
            System.out.println(calcNutrientUtil);
//            controller에서 사용을 위해 return하기
            return calcNutrientUtil;
    }

    public List<DailyTotalVo> selectDaily(long userNumber){

        List<DailyTotalVo> dailyTotalVo = chartMapper.selectDaily(userNumber);

        System.out.println("$$$$$$$$$$$$$$$$$$");
        System.out.println(dailyTotalVo);
        System.out.println("$$$$$$$$$$$$$$$$$$");
        return dailyTotalVo;
    }

    public List<DailyTotalVo> selectWeekly(long userNumber){
        List<DailyTotalVo> dailyTotalVo = chartMapper.selectWeekly(userNumber);
        System.out.println(dailyTotalVo);
        return dailyTotalVo;
    }

    public List<DailyTotalVo> selectMonthly(long userNumber){
        List<DailyTotalVo> dailyTotalVo = chartMapper.selectMonthly(userNumber);
        System.out.println(dailyTotalVo);
        return dailyTotalVo;
    }

    public List<MealVo> getMealList(Long userNumber, String mealTime){
        if (userNumber == null || mealTime == null){
            throw new IllegalArgumentException("유저넘버, 식사 날짜 없음 !");
        }
        return chartMapper.getMealList(userNumber, mealTime);
    }

    public TodayKcalSumVo getMealCodeNumberSum(long userNumber, String mealTime){
        TodayKcalSumVo todayKcalSumVo = chartMapper.selectMealCodeNumberSum(userNumber, mealTime);
        System.out.println(todayKcalSumVo);
        return todayKcalSumVo;
    }

}
