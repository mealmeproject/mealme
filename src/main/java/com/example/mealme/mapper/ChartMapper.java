package com.example.mealme.mapper;

import com.example.mealme.dto.BoardDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.DailyTotalVo;
import com.example.mealme.vo.FoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChartMapper {
    public UserDto selectUserInfo(Long userNumber);
    public List<FoodVo> selectFoodInfo(@Param("userNumber") Long userNumber, @Param("mealTime") String mealTime);
    public List<DailyTotalVo> selectDaily(Long userNumber);
}
