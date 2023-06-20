package com.example.mealme.controller.admin;

import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.meal.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/admin")
    public void admin(){}

    @GetMapping("/admin2")
    public void admin2(){}

    @GetMapping("/admin3")
    public void admin3(){}

    @GetMapping("/admin4")
    public void admin4(){}

    @GetMapping("/admin5")
    public void admin5(){}

    @GetMapping("/admin6")
    public void admin6(){}

    @GetMapping("/admin7")
    public void admin7(){}

    @GetMapping("/admin8")
    public void admin8(){}

}
