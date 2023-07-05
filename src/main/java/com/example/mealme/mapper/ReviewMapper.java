package com.example.mealme.mapper;

import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.ConsultingReviewVo;
import com.example.mealme.vo.DailyTotalVo;
import com.example.mealme.vo.FoodVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    public ConsultingReviewVo selectConsultingInfo(Long consultingRequestNumber);
    public void insertConsultingReview(ConsultingReviewVo consultingReviewVo);
}
