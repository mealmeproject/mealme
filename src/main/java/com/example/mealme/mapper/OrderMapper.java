package com.example.mealme.mapper;

import com.example.mealme.vo.DailyOrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<DailyOrderVo> selectOrder();
    List<DailyOrderVo> selectPayment();
    List<DailyOrderVo> selectRefund();
}
