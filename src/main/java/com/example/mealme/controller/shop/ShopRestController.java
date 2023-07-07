package com.example.mealme.controller.shop;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.CartVo;
import com.example.mealme.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shops/*")
@RequiredArgsConstructor
public class ShopRestController {
    private final ShopService shopService;

    @GetMapping("/allList")
    public List<ProductVo> allList() {
        List<ProductVo> selectAllPrice = shopService.selectAllPrice();

        return selectAllPrice;
    }

    @GetMapping("/reviewPriceList")
    public List<ProductVo> reviewPriceList() {
        List<ProductVo> selectReviewPrice = shopService.selectReviewPrice();

        return selectReviewPrice;
    }

    @GetMapping("/lowPriceList")
    public List<ProductVo> lowPriceList() {
        List<ProductVo> selectLowPrice = shopService.selectLowPrice();

        return selectLowPrice;
    }

//    @PostMapping("/addCart")
//    public String addCart(@RequestParam("userNumber") String userNumber,
//                          @RequestParam("productNumber") String productNumber,
//                          HttpSession session) {
//
//        List<CartVo> selectCart = shopService.addCart();
//
//        Long userNumberValue = (Long) session.getAttribute("userNumber");
//        Long productNumberValue = (Long) session.getAttribute("productNumber");
//
//        shopService.addCart(userNumber, productNumber);
//
//        System.out.println("userNumber: " + userNumberValue);
//        System.out.println("productNumber: " + productNumberValue);
//
//        return "shop/addCart";
//    }

    @PostMapping("/addCart")
    public RedirectView addCart(CartVo cartVo, HttpServletRequest req, RedirectAttributes redirectAttributes) {

        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        cartVo.setUserNumber(userNumber);
        shopService.addCart(cartVo);

        redirectAttributes.addFlashAttribute("userNumber", cartVo.getUserNumber());


        return new RedirectView("/shop/shoppingBasket");

    }
}


