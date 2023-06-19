package com.example.mealme.controller.admin;

import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.meal.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

}
