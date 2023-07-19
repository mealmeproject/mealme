package com.example.mealme.controller.shop;

import com.example.mealme.dto.OrderDto;
import com.example.mealme.dto.ProductDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shops/*")
@RequiredArgsConstructor
public class ShopRestController {
    private final ShopService shopService;

//    @GetMapping("/protein")
//    public List<ProductVo> protein(){
//        List<ProductVo> selectProtein = shopService.selectCategory();
//
//        return selectProtein;
//    }
//    @GetMapping("/lowCalory")
//    public List<ProductVo> lowCalory(){
//        List<ProductVo> selectLowCalory = shopService.selectCategory();
//        return selectLowCalory;
//    }
//    @GetMapping("/proteinShake")
//    public List<ProductVo> proteinShake(){
//        List<ProductVo> selectProteinShake = shopService.selectCategory();
//
//        return selectProteinShake;
//    }
//    @GetMapping("/proteinBar")
//    public List<ProductVo> proteinBar(){
//        List<ProductVo> selectProteinBar = shopService.selectCategory();
//
//        return selectProteinBar;
//    }
//    @GetMapping("/vitamin")
//    public List<ProductVo> vitamin(){
//        List<ProductVo> selectVitamin = shopService.selectCategory();
//
//        return selectVitamin;
//    }
//    @GetMapping("/lactobacillus")
//    public List<ProductVo> lactobacillus(){
//        List<ProductVo> selectLactobacillus = shopService.selectCategory();
//
//        return selectLactobacillus;
//    }
//    @GetMapping("/magnesium")
//    public List<ProductVo> magnesium(){
//        List<ProductVo> selectMagnesium = shopService.selectCategory();
//
//        return selectMagnesium;
//    }


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


//    @GetMapping("/selectCart")
//    public CartVo selectOne(){
//         shopService.selectOne(selectOne().getProductName());
//
//        return selectOne();
//
//    }

    @DeleteMapping("/deleteCart")
    public void deleteCart(@RequestParam("cartNumbers") List<Long> cartNumbers) {
        shopService.deleteCart(cartNumbers);
    }

    @PostMapping("/updateCartPlus")
    public void updateCartPlus(@RequestParam("cartCount") int cartCount, Long cartNumber) {
        CartVo cartVo = new CartVo();
        cartVo.setCartCount(cartCount);
        cartVo.setCartNumber(cartNumber);
        shopService.updateCartPlus(cartVo);
        System.out.println("========합하기 성공=======");
        System.out.println(cartVo);
        System.out.println(cartNumber);
    }

    @PostMapping("/updateCartMinus")
    public void updateCartMinus(@RequestParam("cartCount") int cartCount) {
        CartVo cartVo = new CartVo();
        cartVo.setCartCount(cartCount);
        shopService.updateCartMinus(cartVo);
        System.out.println("=======빼기 성공=======");
        System.out.println(cartVo);
    }




//    @PostMapping("/updateCartMinus")
//    public int updateCartMinus(Long cartNumber){
//        return shopService.updateCartMinus(cartNumber);
//    }

    @PostMapping("/payList" )
    public List<OrderDto> insertPayList(@RequestBody ShippingVo shippingVo, HttpServletRequest req) {
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");

        shippingVo.setUserNumber(userNumber);
        shippingVo.getOrderDtoList().stream().forEach(dto -> dto.setUserNumber(userNumber));
//       List<ProductPayVo> insertPay =  shopService.insertPay();

        //현재 shoppingAddress3가 화면에서 이력되지 않아 임시로 넣음
        shippingVo.setShippingAddress3("(임시)");

        System.out.println("sadasdasdasdasdasd");
        System.out.println(shippingVo);
//        return insertPay;

        return shopService.paymentProcess(shippingVo);
    }

    @GetMapping("/userInfo")
    public UserDto getUserInfo(HttpServletRequest req){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        return shopService.findUser(userNumber);
    }

}
