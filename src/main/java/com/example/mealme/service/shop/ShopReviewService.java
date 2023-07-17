package com.example.mealme.service.shop;

import com.example.mealme.mapper.ReviewMapper;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopReviewService {
    private final ReviewMapper reviewMapper;

//    상품 구매내역 리스트
    public List<ProductPayListVo> findProductOrderList(Long userNumber, Criteria criteria){
    List<ProductPayListVo> productOrderList = reviewMapper.selectOrderProductList(userNumber, criteria);
    System.out.println("@@@@@상품 구매내역 리스트@@@@@");
    System.out.println(productOrderList);
    return productOrderList;
    }

    public int orderProductListCount(){ return reviewMapper.orderProductListCount(); }

    public ProductReviewVo findProductInfo(Long orderNumber){
    ProductReviewVo productReviewVo = reviewMapper.selectProductInfo(orderNumber);
    return productReviewVo;
    }

    public void register(ProductReviewVo productReviewVo){
    if(productReviewVo.getReviewContent() == null || productReviewVo.getReviewGrade() == 0){
        throw new IllegalArgumentException("게시물 정보가 없습니다.");
    }
    reviewMapper.insertProductReview(productReviewVo);
    }

    public void updateOrderCondition(Long orderNumber){
        reviewMapper.updateOrderCondition(orderNumber);
    }

    public List<ProductReviewVo> findProductReviewList(Long userNumber){
        List<ProductReviewVo> productReviewList = reviewMapper.selectProductReviewList(userNumber);
        System.out.println("productReviewList");
        return productReviewList;
    }

    public ProductReviewVo findProductReviewInfo(Long reviewNumber){
        ProductReviewVo productReviewVo = reviewMapper.selectProductReviewInfo(reviewNumber);
        return productReviewVo;
    }

    public void modifyProductReview(ProductReviewVo productReviewVo){
        if(productReviewVo == null){
            throw new IllegalArgumentException("상품 리뷰의 수정 정보가 없습니다.");
        }
        reviewMapper.updateProductReview(productReviewVo);
    }

    public void removeProductReview(Long reviewNumber){
        if(reviewNumber == null){
            throw new IllegalArgumentException("존재하지 않는 상품 리뷰입니다.");
        }
        reviewMapper.deleteProductReview(reviewNumber);
    }


}


