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

    @GetMapping("/shoppingList")
    public List<ProductVo> shoppingList(@RequestParam("orderBy") String orderBy, @RequestParam("ascending") boolean ascending) {
        // orderBy와 ascending 값을 사용하여 Criteria 객체 생성 및 쿼리 수행
        Criteria criteria = new Criteria();
        criteria.setOrderBy(orderBy);
        criteria.setAscending(ascending);

        List<ProductVo> products = shopService.getProductsByCriteria(criteria);

        return products;
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

    private class Criteria {
    }
}
