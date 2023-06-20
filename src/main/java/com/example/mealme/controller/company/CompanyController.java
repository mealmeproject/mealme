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


    @GetMapping("/ConsultingList")
    public void ConsultingList() {

    }

    @GetMapping("/ConsultingList2")
    public void ConsultingList2() {

    }

    @GetMapping("/SendConsulting")
    public void SendConsulitng() {

    }

    @GetMapping("/WhyRefund")
    public void WhyRefund() {

    }

    @GetMapping("/SendReadList")
    public void SendReadList() {
    }

    @GetMapping("/SendReadList2")
    public void SendReadList2() {
    }

}
