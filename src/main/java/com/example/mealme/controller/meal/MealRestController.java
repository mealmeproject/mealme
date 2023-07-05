package com.example.mealme.controller.meal;

import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.ReadMealVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meal/*")
public class MealRestController {
    private final MealService mealService;


    @GetMapping("/myPage/{boardNumber}")
    public ReadMealVo readMeal(@PathVariable("boardNumber") Long boardNumber){
        System.out.println("readMeal 컨트롤러 들어옴 !");
        System.out.println(boardNumber);
        ReadMealVo readMealVo = mealService.readMeal(boardNumber);

        return readMealVo;
    }
}
