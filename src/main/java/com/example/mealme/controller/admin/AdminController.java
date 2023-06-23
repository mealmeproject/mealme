package com.example.mealme.controller.admin;

import com.example.mealme.dto.UserDto;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.meal.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/adminMain")
    public void adminMain(){}

    @GetMapping("/userList")
    public void userList(){
    }

    @GetMapping("/companyList")
    public void companyList(){}

    @GetMapping("/orderList")
    public void orderList(){}

    @GetMapping("/productList")
    public void productList(){}

    @GetMapping("/refundList")
    public void refundList(){}

    @GetMapping("/registProduct")
    public void registProduct(){}

    @GetMapping("/registChange")
    public void registChange(){}

}
