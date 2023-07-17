package com.example.mealme.mapper;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.io.CharArrayWriter;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {



    //전체 조회(별점,총 개수,상품 이미지)
    List<ProductVo> selectAll(@Param("criteria") Criteria criteria);

    //    카테고리별 상품 조회

    List<ProductVo> selectCategory(@Param("categoryCode1") Long categoryCode1, @Param("categoryCode2") Long categoryCode2);

    //전체 음식 조회
    List<ProductVo> selectAllPrice();


    //    //전체 게시글 수 조회
    int selectTotal();

    //낮은 가격 순으로 조회
    List<ProductVo> selectLowPrice();

    //높은 가격 순으로 조회
    List<ProductVo> selectReviewPrice();

    //상품 상세정보 조회
    List<ProductVo> selectList(ProductVo productVo);

    //상품 별점 조회
    ProductVo selectStarRating(String productNumber);

    // 장바구니 상품 조회
    List<CartVo> selectCart(CartVo cartVo);



    //장바구니 상품 추가
    void addCart(CartVo cartVo);

    //장바구니 상품 삭제
    void deleteCart(Long cartNumber);


    //장바구니 상품 수량 변경
    void updateCartPlus(CartVo cartVo);

    //장바구니 상품 수량 변경
    void updateCartMinus(CartVo cartVo);


    // 결제
    List<ProductPayVo> insertPay();



//    주문내역 상세정보
    List<OrderInfoVo> orderInfo();

    //유저 정보 불러오기
    UserDto selectUser(Long userNumber);

//    List<ProductVo> selectCategory(CriteriaProduct criteriaProduct);

    List<CartVo> selectCartList(Long userNumber);
}


