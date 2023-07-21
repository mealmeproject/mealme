package com.example.mealme.controller.admin;

import com.example.mealme.dto.*;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.service.admin.OrderService;
import com.example.mealme.service.admin.ProductFileService;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/v1/*")
public class AdminRestController {
    private final AdminService adminService;
    private final ProductFileService productFileService;
    private final OrderService orderService;

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
        int userTotal = adminService.userTotal();
        int searchTotal = adminService.getTotal(searchVo);


        Map<String, Object> findUser = new HashMap<>();
        findUser.put("pageVo", pageVo);
        findUser.put("userList", searchUser);
        findUser.put("userTotal", userTotal);
        findUser.put("searchTotal", searchTotal);
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
    public Map<String, Object> findCompanyList(@PathVariable("page") int page, SearchCompanyVo searchCompanyVo) {
        Criteria criteria = new Criteria(page, 10);

        System.out.println("====================================================================================");
        System.out.println(searchCompanyVo);
        System.out.println("====================================================================================");

        PageVo pageVo = new PageVo(criteria, adminService.getCompanyTotal(searchCompanyVo));
        List<CompanyDto> searchCompany = adminService.searchCompanyList(searchCompanyVo, criteria);
        int companyTotal = adminService.companyCountTotal();
        int searchTotal = adminService.getCompanyTotal(searchCompanyVo);


        Map<String, Object> findCompany = new HashMap<>();
        findCompany.put("pageVo", pageVo);

        findCompany.put("companyList", searchCompany);
        findCompany.put("companyTotal", companyTotal);
        findCompany.put("searchTotal",searchTotal);
        return findCompany;
    }

    @GetMapping("/searchProductList/{page}")
    public Map<String, Object> findProductList(@PathVariable("page") int page, SearchProductVo searchProductVo) {
        Criteria criteria = new Criteria(page, 10);

        System.out.println("====================================================================================");
        System.out.println(searchProductVo);
        System.out.println("====================================================================================");

        PageVo pageVo = new PageVo(criteria, adminService.getProductTotal(searchProductVo));
        List<ProductVo> searchProduct = adminService.searchProductList(searchProductVo, criteria);
        int searchTotal = adminService.getProductTotal(searchProductVo);
        int productTotal = adminService.productCountTotal();


        Map<String, Object> findProduct = new HashMap<>();
        findProduct.put("pageVo", pageVo);
        findProduct.put("productList", searchProduct);
        findProduct.put("searchTotal", searchTotal);
        findProduct.put("productTotal", productTotal);

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

    @GetMapping("/searchOrderList/{page}")
    public Map<String, Object> findOrderList(@PathVariable("page") int page, SearchProductVo searchProductVo, Long orderNumber) {
        Criteria criteria = new Criteria(page, 10);

        System.out.println("====================================================================================");
        System.out.println(searchProductVo);
        System.out.println("====================================================================================");

        PageVo pageVo = new PageVo(criteria, adminService.findOrderTotal(searchProductVo));
        List<OrderVo> searchOrder = adminService.findOrderList(searchProductVo, criteria);
        int orderTotal = adminService.orderCountTotal();
        int searchTotal = adminService.findOrderTotal(searchProductVo);


        Map<String, Object> findOrder = new HashMap<>();
        findOrder.put("pageVo", pageVo);
        findOrder.put("orderList", searchOrder);
        findOrder.put("orderTotal", orderTotal);
        findOrder.put("searchTotal", searchTotal);
        return findOrder;
    }

    @PostMapping("/modify")
    public void conditionModify(@RequestBody Map<String, Object> orderNumber){
        System.out.println(orderNumber);
        List<String> numberList = (List<String>) orderNumber.get("orderNumber");
        Long orderConditionCode = Long.parseLong((String)orderNumber.get("orderConditionCode"));

        adminService.modifyStatus(orderConditionCode,numberList);
    }

//    @GetMapping("/modifyStatus/{page}")
//            public Map<String,Object> viewModify(@PathVariable("page") int page, SearchCompanyVo searchCompanyVo) {
//        Criteria criteria = new Criteria(page, 10);
//
//        System.out.println("====================================================================================");
//        System.out.println(searchCompanyVo);
//        System.out.println("====================================================================================");
//
//        PageVo pageVo = new PageVo(criteria, adminService.getCompanyTotal(searchCompanyVo));
//        List<CompanyDto> searchCompany = adminService.searchCompanyList(searchCompanyVo, criteria);
//        int companyTotal = adminService.companyCountTotal();
//        int searchTotal = adminService.getCompanyTotal(searchCompanyVo);
//
//
//        Map<String, Object> findCompany = new HashMap<>();
//        findCompany.put("pageVo", pageVo);
//
//        findCompany.put("companyList", searchCompany);
//        findCompany.put("companyTotal", companyTotal);
//        findCompany.put("searchTotal", searchTotal);
//        return findCompany;
//    }

    @PostMapping("/modifyStatus")
    public void statusModify(@RequestBody Map<String, Object> companyNumber){
        System.out.println(companyNumber);
        List<String> numberList = (List<String>) companyNumber.get("companyNumber");
        Long companyStatus = Long.parseLong((String)companyNumber.get("companyStatus"));

        adminService.modifyCompanyStatus(companyStatus,numberList);
    }

    @PostMapping("/modifyStatus2")
    public void statusCancel(@RequestBody Map<String, Object> companyNumber){
        System.out.println(companyNumber);
        List<String> numberList = (List<String>) companyNumber.get("companyNumber");
        Long companyStatus = Long.parseLong((String)companyNumber.get("companyStatus"));

        adminService.modifyCompanyStatus(companyStatus,numberList);
    }

    @GetMapping("/modifyName")
    public Map<String, Object> modifyName(Long orderConditionCode){
       String conditionName = adminService.getConditionName(orderConditionCode);
       Map<String, Object> result = new HashMap<>();
       result.put("conditionName", conditionName);
       return result;
    }


//    주문
    @GetMapping("/dailyOrder")
    public List<DailyOrderVo> dailyOrder(){

        List<DailyOrderVo> dailyOrders = orderService.dailyOrder();
        return dailyOrders;


    }

    @GetMapping("/weeklyTotal")
    public Map<String,Object> weeklyTotal(){
        List<DailyOrderVo> weeklyOrder = orderService.weeklyOrder();
        List<DailyOrderVo> weeklyPayment = orderService.weeklyPayment();
        List<DailyOrderVo> weeklyRefund = orderService.weeklyRefund();
        Map<String,Object> weeklyTotal = new HashMap<>();

        weeklyTotal.put("weeklyOrder", weeklyOrder);
        weeklyTotal.put("weeklyPayment", weeklyPayment);
        weeklyTotal.put("weeklyRefund", weeklyRefund);


        return weeklyTotal;
    }

    @GetMapping("/monthlyTotal")
    public Map<String,Object> monthlyTotal(){
        List<DailyOrderVo> monthlyOrder = orderService.monthlyOrder();
        List<DailyOrderVo> monthlyPayment = orderService.monthlyPayment();
        List<DailyOrderVo> monthlyRefund = orderService.monthlyRefund();
        Map<String,Object> monthlyTotal = new HashMap<>();

        monthlyTotal.put("monthlyOrder", monthlyOrder);
        monthlyTotal.put("monthlyPayment", monthlyPayment);
        monthlyTotal.put("monthlyRefund", monthlyRefund);


        return monthlyTotal;
    }

    @GetMapping("/weeklyChart")
    public Map<String,Object> weeklyChart(){
        List<DailyOrderVo> weeklyOrder = orderService.weeklyOrder();
        List<DailyOrderVo> weeklyPayment = orderService.weeklyPayment();
        List<DailyOrderVo> weeklyRefund = orderService.weeklyRefund();
        Map<String,Object> weeklyTotal = new HashMap<>();

        weeklyTotal.put("weeklyOrder", weeklyOrder);
        weeklyTotal.put("weeklyPayment", weeklyPayment);
        weeklyTotal.put("weeklyRefund", weeklyRefund);


        return weeklyTotal;
    }


//    결제
    @GetMapping("/dailyPayment")
    public List<DailyOrderVo> dailyPayment(){
        List<DailyOrderVo> dailyPayments = orderService.dailyPayment();
        return dailyPayments;

    }

    @GetMapping("/weeklyPayment")
    public List<DailyOrderVo> weeklyPayment(){
        List<DailyOrderVo> weeklyPayment = orderService.weeklyPayment();
        return weeklyPayment;
    }


//    환불
    @GetMapping("/dailyRefund")
    public List<DailyOrderVo> dailyRefund(){
        List<DailyOrderVo> dailyRefunds = orderService.dailyRefund();
        return dailyRefunds;

    }

    @GetMapping("/weeklyRefund")
    public List<DailyOrderVo> weeklyRefund(){
        List<DailyOrderVo> weeklyRefunds = orderService.weeklyRefund();
        return weeklyRefunds;
    }

    @GetMapping("/statusCount")
    public List<OrderDto> statusCount(){
        List<OrderDto> statusCount = adminService.getStatusCount();
        return statusCount;
    }

    @GetMapping("/totalCount")
    public UserTotalVo userTotalCount(){
       return adminService.getUserTotal();

    }

    @GetMapping("/userTotal")
    public Map<String,Object> userTotal(SearchVo searchVo){
        int userTotal = adminService.userTotal();
        int searchTotal = adminService.getTotal(searchVo);
        Map<String,Object> totalCount = new HashMap<>();
        totalCount.put("userTotal", userTotal);
        totalCount.put("searchTotal", searchTotal);
        return totalCount;
    }

}