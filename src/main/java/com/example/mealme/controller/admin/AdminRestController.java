package com.example.mealme.controller.admin;

import com.example.mealme.dto.*;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.admin.ProductFileService;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/v1/*")
public class AdminRestController {
    private final AdminService adminService;
    private final ProductFileService productFileService;

    @GetMapping("/user")
    public List<UserDto> showUserList() {
        return adminService.findAll();
    }

//    @GetMapping("/searchUserList")
//    public List<UserDto> searchUserList(String keyword, String searchType){
//        SearchVo searchVo = new SearchVo();
//        searchVo.setKeyword(keyword);
//        searchVo.setSearchType(searchType);
//        return adminService.searchUserList(searchVo);
//    }


    @PostMapping(value = "/deleteUserList")
    public Map<String, List<String>> deleteUserList(@RequestBody Map<String, List<String>> checkBoxArr) {
        System.out.println("=============================================================");
        System.out.println(checkBoxArr);
        List<String> numberList = checkBoxArr.get("checkBoxArr");
//        adminService.deleteUserList(numberList);


        String checkNum = "";
        for (String str : numberList) {
            checkNum = str;
            adminService.deleteUserList(Collections.singletonList(checkNum));
        }
        return checkBoxArr;
    }

    @GetMapping("/searchUserList/{page}")
    public Map<String, Object> findUserList(@PathVariable("page") int page, SearchVo searchVo) {
        Criteria criteria = new Criteria(page, 10);

        System.out.println("====================================================================================");
        System.out.println(searchVo);
        System.out.println("====================================================================================");

        PageVo pageVo = new PageVo(criteria, adminService.getTotal(searchVo));
        List<UserDto> searchUser = adminService.searchUserList(searchVo, criteria);

//        List<UserDto> userList = adminService.findAll(criteria);
        Map<String, Object> findUser = new HashMap<>();
        findUser.put("pageVo", pageVo);
//        findUser.put("userList", userList);
        findUser.put("userList", searchUser);
        return findUser;
    }

    @GetMapping("/categoryList")
    public List<ProductCategory1Dto> showCategory() {
        return adminService.findCategory();
    }

    @GetMapping("/categoryName")
    public List<ProductCategory2Dto> showCategory2(Long categoryCode1) {
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println(categoryCode1);
        System.out.println("===============================");
        return adminService.findCategory2(categoryCode1);
    }


    @GetMapping("/searchCompanyList/{page}")
    public Map<String, Object> findCompanyList(@PathVariable("page") int page, SearchVo searchVo) {
        Criteria criteria = new Criteria(page, 10);

        System.out.println("====================================================================================");
        System.out.println(searchVo);
        System.out.println("====================================================================================");

        PageVo pageVo = new PageVo(criteria, adminService.getCompanyTotal(searchVo));
        List<CompanyDto> searchCompany = adminService.searchCompanyList(searchVo, criteria);


        Map<String, Object> findCompany = new HashMap<>();
        findCompany.put("pageVo", pageVo);

        findCompany.put("companyList", searchCompany);
        return findCompany;
    }

    @GetMapping("/searchProductList/{page}")
    public Map<String, Object> findProductList(@PathVariable("page") int page, SearchProductVo searchProductVo, Long productNumber) {
        Criteria criteria = new Criteria(page, 10);

        System.out.println("====================================================================================");
        System.out.println(searchProductVo);
        System.out.println("====================================================================================");

        PageVo pageVo = new PageVo(criteria, adminService.getProductTotal(searchProductVo));
        List<ProductVo> searchProduct = adminService.searchProductList(searchProductVo, criteria);


        Map<String, Object> findProduct = new HashMap<>();
        findProduct.put("pageVo", pageVo);
        findProduct.put("productList", searchProduct);

        return findProduct;
    }

//    @PostMapping("/modify/{productNumber}")
////    public String productModify(@RequestBody ProductVo productVo, @PathVariable("productNumber") Long productNumber){
//////        try {
//////            adminService.modify(productVo, files);
//////        } catch (IOException e) {
//////            e.printStackTrace();
//////        }
//////        redirectAttributes.addAttribute("boardNumber", boardDto.getBoardNumber());
//////        return new RedirectView("/board/view");
//////    }
////}

}