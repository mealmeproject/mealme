package com.example.mealme.service.meal;

import com.example.mealme.mapper.MealMapper;
import com.example.mealme.util.MealUtil;
import com.example.mealme.vo.DayMealVo;
import com.example.mealme.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MealService {
    private final MealMapper mealMapper;


    // 마이페이지 상단 유저 정보
    public UserInfoVo findUserInfo(Long userNumber){
        if (userNumber == null){
            throw new IllegalArgumentException("userNumber 없음!");
        }
        return Optional.ofNullable(mealMapper.userInfo(userNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 유저입니다.");});
    }


    // 식단전체 리스트를 일별리스트로 바꿔 주ㄱl
    public Map<String, List<DayMealVo>> getDayMealList(Long userNumber){
        if (userNumber == null){
            throw new IllegalArgumentException("userNumber 없음 ! ");
        }

        return Optional.ofNullable(MealUtil.getMealList(mealMapper.getMealList(userNumber)))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 유저");});
    }






}
