package com.example.mealme.controller.shop;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.ProductVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/shop/*")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/shoppingList{products}")
    public List<ProductDto> shoppingList() {
        List<ProductDto> foodCategory = shopService.foodCategory("음식");
        List<ProductDto> supplementCategory = shopService.foodCategory("보충제");
        List<ProductDto> nutrientsCategory = shopService.foodCategory("영양제");
        List<ProductVo> products = shopService.selectAll();
        List<ProductVo> selectAllPrice = shopService.selectAllPrice();
        List<ProductVo> selectLowPrice = shopService.selectLowPrice();
        List<ProductVo> selectReviewPrice = shopService.selectReviewPrice();

        List<ProductDto> productDtoList = new ArrayList<>();
        for (ProductVo product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setProductNumber(product.getProductNumber());
            productDto.setProductName(product.getProductName());
            productDto.setProductPrice(product.getProductPrice());
            productDto.setProductSeller(product.getProductSeller());


            productDtoList.add(productDto);
        }


        return productDtoList;
}


    @GetMapping("/shoppingDetail")
    public void shoppingDetail(Model model){

        List<ProductVo> selectAll = shopService.selectAll();

        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("products", selectAll);
        model.addAttribute("totalCount", selectAll);
        model.addAttribute("starRatings", selectAll);
        model.addAttribute("productImg", selectAll);


        System.out.println(selectAll);

    }


    @GetMapping("/shoppingBasket")
    public void shoppingBasket(){




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
