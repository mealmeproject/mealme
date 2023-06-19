package com.example.mealme.controller.user;

import com.example.mealme.service.meal.MealService;
import com.example.mealme.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public void login(){}

    @GetMapping("/userOrCompany")
    public void userOrCompany(){}

    @GetMapping("/userJoin")
    public void userJoin(){}

    @GetMapping("/userJoinOk")
    public void userJoinOk(){}

    @GetMapping("/companyJoin")
    public void companyJoin(){}

    @GetMapping("/companyJoinOk")
    public void companyJoinOk(){}

    @GetMapping("/findPassword")
    public void findPassword(){}

    @GetMapping("/findPasswordOk")
    public void findPasswordOk(){}

    @GetMapping("/userUpdate")
    public void userUpdate(){}

    @GetMapping("/userUpdate2")
    public void userUpdate2(){}

    @GetMapping("/companyUpdate")
    public void companyUpdate(){}

    @GetMapping("/companyUpdate2")
    public void companyUpdate2(){}

}

