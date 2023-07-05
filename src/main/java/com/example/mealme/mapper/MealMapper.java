package com.example.mealme.mapper;

import com.example.mealme.dto.BoardFileDto;
import com.example.mealme.dto.FoodDto;
import com.example.mealme.vo.MealVo;
import com.example.mealme.vo.ReadMealVo;
import com.example.mealme.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MealMapper {

    // 마이페이지 유저 정보
    public UserInfoVo userInfo(Long userNumber);

    // 마이페이지 유저 식단
    public List<MealVo> getMealList(Long userNumber);

    // 마이페이지 모달창 식사 정보
    public ReadMealVo getMeal(Long boardNumber);

    // 마이페이지 모달창 음식 정보들
    public List<FoodDto> getFoods(Long boardNumber);

    // 마이페이지 모달창 음식 파일들
    public List<BoardFileDto> getFiles(Long boardNumber);
}
