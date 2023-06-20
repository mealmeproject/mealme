package com.example.mealme.controller.company;

import com.example.mealme.service.company.CompanyService;
import com.example.mealme.service.meal.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company/*")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/largeCategory")
    public void largeCategory(){}

    @GetMapping("/hospitalCategory")
    public void hospitalCategory(){}

    @GetMapping("/detailedHospital")
    public void detailedHospital(){}

    @GetMapping("/settingThePeriod")
    public void settingThePeriod(){}
}
