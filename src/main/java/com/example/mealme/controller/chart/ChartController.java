package com.example.mealme.controller.chart;

import com.example.mealme.dto.UserDto;
import com.example.mealme.service.chart.ChartService;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.util.CalcNutrientUtil;
import com.example.mealme.vo.DailyTotalVo;
import com.example.mealme.vo.FoodVo;
import com.example.mealme.vo.RecommendVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chart/*")
@RequiredArgsConstructor
public class ChartController {
    private final ChartService chartService;


    @GetMapping("/chartMonthly")
    public String chartMonthly(HttpServletRequest req, Model model){
        Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber = 1L;
        List<DailyTotalVo> dailyTotalVo = chartService.selectMonthly(userNumber);
        RecommendVo recommendVo = chartService.selectUserInfo(userNumber);

        System.out.println("&&&&&&&&&&&&&&&&&&");
        System.out.println(dailyTotalVo);
        System.out.println("%%%%%%%%%%%%%%%%%%%%");
        System.out.println(recommendVo);
        System.out.println("&&&&&&&&&&&&&&&&&&");

        model.addAttribute("DailyTotalVo", dailyTotalVo);
        model.addAttribute("recommendVo", recommendVo);
        return "chart/chartMonthly";
    }

    @GetMapping("/chartWeekly")
    public String chartWeekly(HttpServletRequest req, Model model){
                Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber = 1L;
        List<DailyTotalVo> dailyTotalVo = chartService.selectWeekly(userNumber);
        RecommendVo recommendVo = chartService.selectUserInfo(userNumber);

//        System.out.println("&&&&&&&&&&&&&&&&&&");
//        System.out.println(dailyTotalVo);
//        System.out.println("%%%%%%%%%%%%%%%%%%%%");
//        System.out.println(recommendVo);
//        System.out.println("&&&&&&&&&&&&&&&&&&");
        model.addAttribute("DailyTotalVo", dailyTotalVo);
        model.addAttribute("recommendVo", recommendVo);
        return "chart/chartWeekly";

    }

    @GetMapping("/chartDaily")
    public String chartDaily(HttpServletRequest req, Model model){
        Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber = 1L;
        List<DailyTotalVo> dailyTotalVo = chartService.selectDaily(userNumber);
        RecommendVo recommendVo = chartService.selectUserInfo(userNumber);

//        System.out.println("&&&&&&&&&&&&&&&&&&");
//        System.out.println(dailyTotalVo);
//        System.out.println("%%%%%%%%%%%%%%%%%%%%");
//        System.out.println(recommendVo);
//        System.out.println("&&&&&&&&&&&&&&&&&&");
        model.addAttribute("DailyTotalVo", dailyTotalVo);
        model.addAttribute("recommendVo", recommendVo);
        return "chart/chartDaily";
    }

    @GetMapping("/today")
    public String today( HttpServletRequest req, Model model){

        Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber= 1L;
        String mealTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

//        model에 저장하는 객체의 값을 chartService의 메소드를 통해 저장해줌
     RecommendVo recommendVo = chartService.selectUserInfo(userNumber);
     CalcNutrientUtil calcNutrientUtil = chartService.selectFoodInfo(userNumber, mealTime);

//        System.out.println("==========================================================");
//        System.out.println(recommendVo);
//        System.out.println(calcNutrientUtil);
//        System.out.println("==========================================================");

//        model에 RecommendVo, CalcNutrientUtil가 저장
    model.addAttribute("RecommendVo", recommendVo);
    model.addAttribute("CalcNutrientUtil", calcNutrientUtil);
//    Map<String, Object> obj = new HashMap<>();
//    obj.put("recommendVo", recommendVo);
//    obj.put("CalcNutrientUtil", calcNutrientUtil);

    return "chart/today";
    }


    @GetMapping("/recommend")
    public void recommend(){}


}
