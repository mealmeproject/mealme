package com.example.mealme.mapper;

import com.example.mealme.dto.BoardDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.DailyTotalVo;
import com.example.mealme.vo.FoodVo;
import com.example.mealme.vo.MealVo;
import com.example.mealme.vo.TodayKcalSumVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChartMapper {
    public UserDto selectUserInfo(Long userNumber);
    public List<FoodVo> selectFoodInfo(@Param("userNumber") Long userNumber, @Param("mealTime") String mealTime);
    public List<DailyTotalVo> selectDaily(Long userNumber);
    public List<DailyTotalVo> selectWeekly(Long userNumber);
    public List<DailyTotalVo> selectMonthly(Long userNumber);
    public List<MealVo> getMealList(Long userNumber, String mealTime);
    public TodayKcalSumVo selectMealCodeNumberSum(@Param("userNumber") Long userNumber, @Param("mealTime") String mealTime);
}
