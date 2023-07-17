package com.example.mealme.controller.chart;

import com.example.mealme.service.chart.ChartService;
import com.example.mealme.util.CalcNutrientUtil;
import com.example.mealme.vo.DailyTotalVo;
import com.example.mealme.vo.MealVo;
import com.example.mealme.vo.RecommendVo;
import com.example.mealme.vo.TodayKcalSumVo;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charts/*")
public class TodayRestController {

    private final ChartService chartService;


    @GetMapping("/todayChart")
    public Map<String, Object> today(HttpServletRequest req , String mealTime){

        Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber= 1L;

//        model에 저장하는 객체의 값을 chartService의 메소드를 통해 저장해줌
        RecommendVo recommendVo = chartService.selectUserInfo(userNumber);
        CalcNutrientUtil calcNutrientUtil = chartService.selectFoodInfo(userNumber, mealTime);
        List<MealVo> mealList = chartService.getMealList(userNumber, mealTime);
        TodayKcalSumVo todayKcalSumVo = chartService.getMealCodeNumberSum(userNumber, mealTime);

        System.out.println("==========================================================");
        System.out.println(recommendVo);
        System.out.println(calcNutrientUtil);
        System.out.println("==========================================================");
        System.out.println(mealTime);
        System.out.println("====================식사코드===================");
        System.out.println(todayKcalSumVo);
        Map<String, Object> todayChart = new HashMap<>();
        todayChart.put("recommendVo", recommendVo);
        todayChart.put("calcNutrientUtil", calcNutrientUtil);
        todayChart.put("mealList", mealList);
        todayChart.put("todayKcalSumVo", todayKcalSumVo);

        return todayChart;
    }

    @GetMapping("/daily")
    public Map<String, Object> daily(HttpServletRequest req){
                Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber= 1L;
        List<DailyTotalVo> dailyTotalVo = chartService.selectDaily(userNumber);
        Map<String, Object> dailyList = new HashMap<>();
        dailyList.put("dailyTotalVo", dailyTotalVo);
        return dailyList;
    }

    @GetMapping("/weekly")
    public Map<String, Object> weekly(HttpServletRequest req){
                Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber= 1L;
        List<DailyTotalVo> dailyTotalVo = chartService.selectWeekly(userNumber);
        Map<String, Object> weeklyList = new HashMap<>();
        weeklyList.put("dailyTotalVo", dailyTotalVo);
        return weeklyList;
    }

    @GetMapping("/monthly")
    public Map<String, Object> monthly(HttpServletRequest req){
                Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber= 1L;
        List<DailyTotalVo> dailyTotalVo = chartService.selectMonthly(userNumber);
        Map<String, Object> monthlyList = new HashMap<>();
        monthlyList.put("dailyTotalVo", dailyTotalVo);
        return monthlyList;
    }
    @GetMapping("/DailyMealCodeSum")
    public Map<String, Object> dailyMealCodeSum(HttpServletRequest req,String mealTime){
        Long userNumber = (long)req.getSession().getAttribute("userNumber");
        TodayKcalSumVo todayKcalSumVo = chartService.getMealCodeNumberSum(userNumber, mealTime);
        Map<String, Object> todayKcalSum = new HashMap<>();
        todayKcalSum.put("todayKcalSumVo", todayKcalSumVo);
        return todayKcalSum;
    }



}
