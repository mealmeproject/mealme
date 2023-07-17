package com.example.mealme.controller.shop;

import com.example.mealme.service.shop.ShopReviewService;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.*;
import com.example.mealme.vo.ProductPayListVo;
import com.example.mealme.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/*")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    private final ShopReviewService shopReviewService;


//    @Autowired
//    public ShopController(ShopService shopService, ShopReviewService shopReviewService) {
//
//        this.shopService = shopService;
//        this.shopReviewService = shopReviewService;
//    }


    @GetMapping("/shoppingList")
    public String getProductList(Model model) {

        List<ProductVo> productAll = shopService.selectAll();

        List<ProductVo> productAllPrice = shopService.selectAllPrice();

        model.addAttribute("products", productAllPrice);

        model.addAttribute("productAll", productAll);

        return "shop/shoppingList";


    }

    @GetMapping("/shoppingDetail")
    public String shoppingDetail(Model model, @RequestParam("productNumber") String productNumber, HttpSession session) {

        // 세션에서 userNumber를 가져옴

        Long userNumber = (Long)session.getAttribute("userNumber");

        System.out.println("userNumber: " + userNumber);
        System.out.println("productNumber: " + productNumber);

        ProductVo product = shopService.selectOne(productNumber);
        product.setProductNumber(Long.parseLong(productNumber));
        model.addAttribute("product", product);
        model.addAttribute("userId", product.getUserId());
        model.addAttribute("reviewContent", product.getReviewContent());

        // userNumber 값을 모델에 추가
        model.addAttribute("userNumber", userNumber);
        // productNumber 값을 모델에 추가
        model.addAttribute("productNumber", productNumber);

        return "shop/shoppingDetail";
    }



    @GetMapping("/shoppingBasket")
    public String shoppingBasket(HttpSession session, Long productNumber) {
        Long userNumberValue = (Long) session.getAttribute("userNumber");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++" +
                "");
        System.out.println(productNumber);
        System.out.println("userNumber: " + userNumberValue);



        return "shop/shoppingBasket";
    }






    @GetMapping("/shoppingPay")
    public void shoppingPay(){}

    @GetMapping("/shoppingFinish")
    public void shoppingFinish(){}

//    상품 구매내역 리스트 & 리뷰
    @GetMapping("/shoppingPayInfo")
    public String shoppingPayInfo(Criteria criteria, HttpServletRequest req, Model model){
        //      Long userNumber = (long)req.getSession().getAttribute("userNumber");
        Long userNumber = 1L;
        List<ProductPayListVo> productPayListVo = shopReviewService.findProductOrderList(userNumber,criteria);
        System.out.println("%%%상품 구매내역 리스트%%%");
        System.out.println(productPayListVo);
        model.addAttribute("productPayListVo", productPayListVo);
        model.addAttribute("pageInfo", new PageVo(criteria, shopReviewService.orderProductListCount()));
        System.out.println(new PageVo(criteria, shopReviewService.orderProductListCount()));
        return "shop/shoppingPayInfo";
    }

    @GetMapping("/productReview")
    public String productReview(HttpServletRequest req,@RequestParam("orderNumber") Long orderNumber,Model model){
//        Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long orderNumber = 9L;
        System.out.println("@@orderNumber@@@");
        System.out.println(orderNumber);
    ProductReviewVo productReviewVo = shopReviewService.findProductInfo(orderNumber);
    System.out.println("@@orderNumber@@@");
    System.out.println(orderNumber);
    System.out.println(productReviewVo);
    model.addAttribute("productReviewVo", productReviewVo);
    return "shop/productReview";
    }

    @PostMapping("/productReview")
    public RedirectView productReviewWrite(ProductReviewVo productReviewVo){
        shopReviewService.register(productReviewVo);
        shopReviewService.updateOrderCondition(productReviewVo.getOrderNumber());
        System.out.println();
        return new RedirectView("/shop/productReviewList");
    }

    @GetMapping("/productReviewList")
    public void productReviewList(){}

    @ResponseBody
    @GetMapping("/productReviewListData")
    public Map<String, Object> productReviewListData(HttpServletRequest req) {
//      Long userNumber = (long)req.getSession().getAttribute("userNumber");
        Long userNumber = 1L;
        List<ProductReviewVo> productReviewList = shopReviewService.findProductReviewList(userNumber);

        System.out.println("##########");
        System.out.println(productReviewList);
        Map<String, Object> reviewList = new HashMap<>();
        reviewList.put("productReviewList", productReviewList);
        return reviewList;
    }

    @GetMapping("/productReviewModify")
    public String productReviewModify(long reviewNumber, Model model){
        ProductReviewVo productReviewVo = shopReviewService.findProductReviewInfo(reviewNumber);
        model.addAttribute("productReviewVo", productReviewVo);
        System.out.println("#####수정페이지 정보 받아오기#####");
        System.out.println(productReviewVo);
        return "shop/productReviewModify";
    }

    @PostMapping("/productReviewModify")
    public RedirectView productReviewModify(ProductReviewVo productReviewVo){
        shopReviewService.modifyProductReview(productReviewVo);
        System.out.println("&&&&&&&&&&&&&&&&&&&"+productReviewVo.toString());
        return new RedirectView("/shop/productReviewList");
    }

    @GetMapping("/productReviewRemove")
    public RedirectView productReviewRemove(Long reviewNumber){
        shopReviewService.removeProductReview(reviewNumber);
        return new RedirectView("/shop/productReviewList");
    }


    @GetMapping("/shoppingPayInfoError")
    public void shoppingPayError(){}

    @GetMapping("/shoppingPayInfoDetail")
    public void shoppingPayInfoDetail(){}
}
