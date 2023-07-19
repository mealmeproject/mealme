package com.example.mealme.mapper;

import com.example.mealme.dto.OrderDto;
import com.example.mealme.vo.DailyOrderVo;
import com.example.mealme.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<DailyOrderVo> selectOrder();
    List<DailyOrderVo> selectPayment();
    List<DailyOrderVo> selectRefund();
    public void insert(OrderDto orderDto);
    public List<OrderVo> selectOrderListByOrderNumbers(@Param("productNumberList") List<Long> productNumberList);
}
