package com.example.mealme.controller.admin;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.dto.ProductFileDto;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.admin.ProductFileService;
import com.example.mealme.util.AdminUtil;
import com.example.mealme.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    public String adminMain(HttpServletRequest req){
//        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
            return "/admin/adminLogin";
        } else {
            adminNumber = AdminUtil.sessionCheck(req);
        }return "/admin/adminMain";
    }

    @GetMapping("/userList")
    public String userList(HttpServletRequest req){
        //        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
            return "/admin/adminLogin";
        } else {
            adminNumber = AdminUtil.sessionCheck(req);
        }return "/admin/userList";
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
    public String companyList(HttpServletRequest req){
        //        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
            return "/admin/adminLogin";
        } else {
            adminNumber = AdminUtil.sessionCheck(req);
        }return "/admin/companyList";
    }

    @GetMapping("/orderList")
    public String orderList(HttpServletRequest req){
        //        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
        return "/admin/adminLogin";
        } else {
        adminNumber = AdminUtil.sessionCheck(req);
        }return "/admin/orderList";
        }

    @GetMapping("/productList")
    public String productList(HttpServletRequest req){
        //        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
        return "/admin/adminLogin";
        } else {
        adminNumber = AdminUtil.sessionCheck(req);
        }return "/admin/productList";
        }

    @GetMapping("/refundList")
    public String refundList(HttpServletRequest req){
        //        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
        return "/admin/adminLogin";
        } else {
        adminNumber = AdminUtil.sessionCheck(req);
        }return "/admin/refundList";
        }

    @GetMapping("/registProduct")
    public String registProduct(HttpServletRequest req){
        //        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
        return "/admin/adminLogin";
        } else {
        adminNumber = AdminUtil.sessionCheck(req);
        }return "/admin/registProduct";
        }

    @PostMapping("/registProduct")
    public String registProduct(ProductDto productDto, @RequestParam("productFile") List<MultipartFile> files, Model model){

        System.out.println("=======================");
        System.out.println(productDto);
        System.out.println("============================");
        model.addAttribute("productNumber", productDto.getProductNumber());
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
        return "/admin/productList";

    }

    @GetMapping("/registChange")
    public String registChange(@RequestParam("productNumber") Long productNumber, HttpServletRequest req, Model model){
        //        세션체크
        Long adminNumber = 0L;
        if (AdminUtil.sessionCheck(req)==0L) {
            return "/admin/adminLogin";
        } else {
            adminNumber = AdminUtil.sessionCheck(req);
        }
        ProductVo productVo = adminService.modifyProduct(productNumber);
        List<ProductFileDto> files = productFileService.findList(productNumber);
        model.addAttribute("productNumber", productNumber);
        model.addAttribute("productVo", productVo);
        model.addAttribute("files", files);
        return "/admin/registChange";
    }

    @PostMapping("/registChange")
    public String registChange(ProductDto productDto, ProductVo productVo,@RequestParam("productFile") List<MultipartFile> files){

        System.out.println("=======================");
        System.out.println(productDto);
        System.out.println("============================");
        try {
            adminService.changeProduct(productDto,productVo,files);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        return "/admin/productList";
    }

    @GetMapping("/adminLogin")
    public void adminLogin(){}

    @PostMapping("/adminLogin")
    public RedirectView adminLogin(String id, String password, HttpServletRequest req){
        try {
        Long adminNumber = adminService.findAdminNumber(id, password);
        req.getSession().setAttribute("adminNumber", adminNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return new RedirectView("/admin/adminLogin");
        }
        return new RedirectView("/admin/adminMain");
    }

}
