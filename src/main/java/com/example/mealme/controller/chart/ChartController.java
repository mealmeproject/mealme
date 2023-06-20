package com.example.mealme.controller.chart;

import com.example.mealme.service.chart.ChartService;
import com.example.mealme.service.meal.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart/*")
@RequiredArgsConstructor
public class ChartController {
    private final ChartService chartService;


}
