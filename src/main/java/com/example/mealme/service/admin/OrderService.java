package com.example.mealme.service.admin;

import com.example.mealme.mapper.AdminMapper;
import com.example.mealme.mapper.OrderMapper;
import com.example.mealme.vo.DailyOrderVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final AdminMapper adminMapper;

    public List<DailyOrderVo> dailyOrder(){

        return adminMapper.selectOrder();

    }

    public List<DailyOrderVo> dailyPayment(){
        return adminMapper.selectPayment();
    }

    public List<DailyOrderVo> dailyRefund(){
        return adminMapper.selectRefund();
    }

    public List<DailyOrderVo> weeklyOrder(){

        return adminMapper.weeklyOrder();

    }

    public List<DailyOrderVo> weeklyPayment(){

        return adminMapper.weeklyPayment();

    }

    public List<DailyOrderVo> weeklyRefund(){

        return adminMapper.weeklyRefund();

    }

    public List<DailyOrderVo> monthlyOrder(){

        return adminMapper.monthlyOrder();

    }

    public List<DailyOrderVo> monthlyPayment(){

        return adminMapper.monthlyPayment();

    }

    public List<DailyOrderVo> monthlyRefund(){

        return adminMapper.monthlyRefund();

    }
}
