package com.example.mealme.controller.admin;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.admin.ProductFileService;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.Criteria;
import com.example.mealme.vo.PageVo;
import com.example.mealme.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final ProductFileService productFileService;



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

    @PostMapping("/registProduct")
    public void registProduct(ProductDto productDto, @RequestParam("productFile") List<MultipartFile> files){

        System.out.println("=======================");
        System.out.println(productDto);
        System.out.println("============================");
        adminService.registerProduct(productDto);

        System.out.println("=======================");
        System.out.println(productDto);
        System.out.println("============================");

        if(files != null){
            try {
                productFileService.registerAndSaveFiles(files, productDto.getProductNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @GetMapping("/registChange")
    public void registChange(Long productNumber, Model model){
        List<ProductVo> productVo = adminService.modifyProduct(productNumber);
        model.addAttribute("productVo", productVo);
    }

}
