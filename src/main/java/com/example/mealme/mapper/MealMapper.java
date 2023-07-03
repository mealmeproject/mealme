package com.example.mealme.mapper;

import com.example.mealme.vo.MealVo;
import com.example.mealme.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MealMapper {

    public UserInfoVo userInfo(Long userNumber);

    public List<MealVo> getMealList(Long userNumber);
}
