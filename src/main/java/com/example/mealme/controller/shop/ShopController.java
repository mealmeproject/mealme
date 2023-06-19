package com.example.mealme.controller.shop;

import com.example.mealme.service.meal.MealService;
import com.example.mealme.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/*")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/shoppingList")
    public void shoppingList(){}

    @GetMapping("/shoppingBasket")
    public void shoppingBasket(){}

}
