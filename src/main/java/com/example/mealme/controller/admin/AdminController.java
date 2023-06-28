package com.example.mealme.controller.admin;

import com.example.mealme.dto.UserDto;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.Criteria;
import com.example.mealme.vo.PageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @GetMapping("/list")
//    public String showUserList(Criteria criteria, Model model){
//        List<UserDto> userList = adminService.findAll(criteria);
//        model.addAttribute("userList", userList);
//        model.addAttribute("pageInfo", new PageVo(criteria, adminService.getTotal()));
//
//        return "admin/userList";
//
//    }


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
