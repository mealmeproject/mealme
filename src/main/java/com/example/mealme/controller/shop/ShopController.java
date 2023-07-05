package com.example.mealme.controller.shop;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.mapper.ProductMapper;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.CartVo;
import com.example.mealme.vo.ProductVo;
import lombok.RequiredArgsConstructor;
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
    public String getProductList(Model model) {

        List<ProductVo> productAll = shopService.selectAll();

        List<ProductVo> productAllPrice = shopService.selectAllPrice();

        model.addAttribute("products", productAllPrice);

        model.addAttribute("productAll", productAll);

        return "shop/shoppingList";


    }

    @GetMapping("/shoppingDetail/{userNumber}")
    public String shoppingDetail(Model model, @RequestParam("productNumber") String productNumber, HttpSession session) {

        // 세션에서 userNumber를 가져옴
        Long userNumber = (Long) session.getAttribute("userNumber");

        ProductVo product = shopService.selectOne(productNumber);
        model.addAttribute("product", product);
        model.addAttribute("userId", product.getUserId());
        model.addAttribute("reviewContent", product.getReviewContent());

        // userNumber 값을 모델에 추가
        model.addAttribute("userNumber", userNumber);
        // productNumber 값을 모델에 추가
        model.addAttribute("productNumber", productNumber);

        return "shop/shoppingDetail";
    }

    @PostMapping("/shoppingDetail")
    public String shoppingDetail(Model model, @RequestParam("userNumber") String userNumber, @RequestParam("productNumber") String productNumber, HttpSession session) {
        // shoppingDetail 페이지의 로직 구현

        // 세션에 userNumber 저장
        session.setAttribute("userNumber", Long.parseLong(userNumber));

        // userNumber 값을 모델에 추가
        model.addAttribute("userNumber", userNumber);
        // productNumber 값을 모델에 추가
        model.addAttribute("productNumber", productNumber);

        return "shop/shoppingDetail";
    }






    @GetMapping("/shoppingBasket")
    public String shoppingBasket(Model model,@RequestParam("userNumber") String userNumber) {

        List<CartVo> cartList = shopService.selectCart(userNumber);
        model.addAttribute("cartList", cartList);

        return "shop/shoppingBasket";
    }

    @PostMapping("/shoppingBasket")
    public RedirectView addCart(CartVo cartVo, HttpServletRequest req,
                                RedirectAttributes redirectAttributes) {

        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        cartVo.setUserNumber(userNumber);
        shopService.addCart(cartVo);

        redirectAttributes.addFlashAttribute("userNumber", cartVo.getUserNumber());

        // 상품을 장바구니에 추가한 후 장바구니 페이지로 리다이렉트
        return new RedirectView("/shop/shoppingBasket");
    }



    @GetMapping("/shoppingPay")
    public void shoppingPay(){}

    @GetMapping("/shoppingFinish")
    public void shoppingFinish(){}

    @GetMapping("/shoppingPayInfo")
    public void shoppingPayInfo(){}

    @GetMapping("/shoppingPayInfoError")
    public void shoppingPayError(){}

    @GetMapping("/shoppingPayInfoDetail")
    public void shoppingPayInfoDetail(){}
}
