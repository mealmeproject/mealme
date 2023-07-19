package com.example.mealme.controller.shop;


import com.example.mealme.dto.ProductFileDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.service.shop.ShopReviewService;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
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


    @GetMapping("/shoppingList")
    public String shoppingList(Model model) {
        System.out.println("쇼핑몰 상품 리스트==================================================");
        List<ProductListVo> productList = shopService.findProductList();
        int listCount = shopService.getTotal();

        model.addAttribute("productList", productList);
        model.addAttribute("getTotal", listCount);
        return "shop/shoppingList";
    }



    @GetMapping("/shoppingDetail")
    public String shoppingDetail(Model model, @RequestParam("productNumber") Long productNumber) {
        System.out.println("쇼핑몰 상품 세부 페이지==================================================");

//        상품 상세보기 상단 사진과 정보들
        ProductListVo productDetail = shopService.findProductDetail(productNumber);
//        상품 등록시 추가한 제품 사진 파일 리스트
        List<ProductFileDto> files = shopService.findProductFiles(productNumber);
//        리뷰 목록
        List<ProductReviewVo> reviewList = shopService.findReviewList(productNumber);

        model.addAttribute("productDetail", productDetail);
        model.addAttribute("files", files);
        model.addAttribute("reviewList", reviewList);

        return "shop/shoppingDetail";
    }

//    @GetMapping("/selectCategory")
//    public RedirectView selectCategory(@RequestParam("categoryCode2") Long categoryCode2){
//        List<ProductListVo> productList = shopService.findProductListForCategory(categoryCode2);
//        int listCount = shopService.getTotal();
//    }






    @GetMapping("/shoppingBasket")
    public String shoppingBasket(CartVo cartVo, HttpServletRequest req, Model model) {
        Long userNumberValue = (Long) req.getSession().getAttribute("userNumber");

        // 세션체크
        if(userNumberValue == null){
            return "redirect:/user/login";
        }

        // 장바구니 추가 버튼으로 들어왔을 경우에만 카트에 추가하게 설정
        if (cartVo.getProductNumber() != 0){
        cartVo.setUserNumber(userNumberValue);
        // 장바구니에 상품 추가
        cartVo.setCartCount(1);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("productNumber: " + cartVo.getProductNumber());
        System.out.println("userNumber: " + userNumberValue);
        System.out.println("count: " + cartVo.getCartCount());
        shopService.processCart(cartVo);
        }


        // 장바구니에 담긴 상품 조회
        cartVo.setUserNumber(userNumberValue);
        List<CartVo> cartItems = shopService.selectCart(cartVo); //

        // 총 가격 계산
                int totalPrice = cartVo.getTotalPrice();
                for (CartVo cartItem : cartItems) {
                    totalPrice += cartItem.getProductPrice();
                }

        model.addAttribute("cartItems", cartItems);
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

    @GetMapping("/shoppingLikeList")
    public void shoppingLikeList(){}

    @GetMapping("/shoppingFinish")
    public void moveShoppingFinishPage(@RequestParam("orderNumber") List<Long> orderNumberList){
        System.out.println(orderNumberList);
    }
}
