package com.example.mealme.controller.shop;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.ProductMapper;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop/*")
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {

        this.shopService = shopService;

    }


    @GetMapping("/shoppingList")
    public String shoppingList(@RequestParam(value = "sort", required = false) String sort, Model model,ProductVo productVo, PageVo pageVo, Criteria criteria) {
        List<ProductVo> productAll = shopService.selectAll(criteria);
        List<ProductVo> productAllPrice = shopService.selectAllPrice();
        List<ProductVo> productList;

        model.addAttribute("products", productAllPrice);
        model.addAttribute("productAll", productAll);

        model.addAttribute("pageInfo",new PageVo(criteria, shopService.getTotal()));


        System.out.println("==============================");
        System.out.println(new PageVo(criteria, shopService.getTotal()));

        List<ProductVo> productList2 = shopService.selectList(productVo);


        model.addAttribute("productList2", productList2);



        return "shop/shoppingList";
    }



    @GetMapping("/shoppingDetail")
    public String shoppingDetail(ProductVo productVo, Model model, @RequestParam("productNumber") String productNumber, HttpSession session) {

        // 세션에서 userNumber를 가져옴

        ProductVo starRating = shopService.selectStarRating(productNumber);

        Long userNumber = (Long)session.getAttribute("userNumber");

        // userNumber 값이 없을 경우 로그인 페이지로 이동
        if (userNumber == null) {
            return "redirect:/user/login"; // 로그인 페이지 URL
        }
        // userNumber 값을 모델에 추가
        model.addAttribute("userNumber", userNumber);
        // productNumber 값을 모델에 추가
        model.addAttribute("productNumber", productNumber);

        //상품detail에 들어갈 것들
        model.addAttribute("productName",starRating.getProductName());
        model.addAttribute("reviewDate",starRating.getReviewDate());
        model.addAttribute("averageRating",starRating.getAverageRating());
        model.addAttribute("productNumber",starRating.getProductNumber());
        model.addAttribute("productInventory",starRating.getProductInventory());
        model.addAttribute("productExplanation",starRating.getProductExplanation());
        model.addAttribute("productPrice",starRating.getProductPrice());
        model.addAttribute("productRegisterDate",starRating.getProductRegisterDate());
        model.addAttribute("productSeller",starRating.getProductSeller());

        System.out.println("userNumber: " + userNumber);
        System.out.println("productNumber: " + productNumber);
        System.out.println("averageRating: " + starRating.getAverageRating());




        List<ProductVo> productList = shopService.selectList(productVo);

        shopService.selectList(starRating);

        model.addAttribute("productList", productList);

        System.out.println("======");
        System.out.println(productVo);

        return "shop/shoppingDetail";
    }



    @GetMapping("/shoppingBasket")
    public String shoppingBasket(CartVo cartVo, HttpSession session, HttpServletRequest request, Model model) {
        Long userNumberValue = (Long) session.getAttribute("userNumber");
        Long productNumber = Long.parseLong(request.getParameter("productNumber"));

        // 장바구니에 상품 추가
        CartVo product = shopService.addCart(cartVo);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("productNumber: " + productNumber);
        System.out.println("userNumber: " + userNumberValue);

        // 장바구니에 담긴 상품 조회
        List<CartVo> cartItems = shopService.selectCart(cartVo);

        // 총 가격 계산
                int totalPrice = cartVo.getTotalPrice();
                for (CartVo cartItem : cartItems) {
                    totalPrice += cartItem.getProductPrice();
                }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("product", product);
        model.addAttribute("totalPrice", totalPrice);

        System.out.println("sssssssssssssssssssss");
        System.out.println(cartItems);
        System.out.println(totalPrice);

        return "shop/shoppingBasket";
    }






    @GetMapping("/shoppingPay")
    public String shoppingPay(HttpSession session, Model model, ProductPayVo productPayVo) {
        // 사용자 정보 가져오기
        Long userNumber = (Long) session.getAttribute("userNumber");
        UserDto user = shopService.findUser(userNumber);

        // 필요한 사용자 정보를 세션에서 가져와서 모델에 추가
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("userEmail", user.getUserEmail());
        model.addAttribute("userPhoneNumber", user.getUserPhoneNumber());
//        model.addAttribute("user", user);

        List<CartVo> cartList = shopService.findCartList(userNumber);

        model.addAttribute("cartList", cartList);

        int totalPrice = 0;
        for(int i=0; i<cartList.size(); i++){
            totalPrice += cartList.get(i).getProductPrice() * cartList.get(i).getCartCount();
        }

        model.addAttribute("totalPrice", totalPrice);

        // 다른 필요한 로직 수행...


        return "shop/shoppingPay"; // 주문 페이지 뷰 반환
    }









    @GetMapping("/shoppingFinish")
    public void shoppingFinish(){
    }














    @GetMapping("/shoppingPayInfo")
    public void shoppingPayInfo(){}












    @GetMapping("/shoppingPayInfoError")
    public void shoppingPayError(){}







    @GetMapping("/shoppingPayInfoDetail")
    public void shoppingPayInfoDetail(){}
}
