package com.example.mealme.mapper;

import com.example.mealme.dto.ProductFileDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    List<ProductReviewVo> selectReviewList(Long productNumber);

    //상품 별점 조회
    ProductVo selectStarRating(String productNumber);

    // 장바구니 상품 조회
    List<CartVo> selectCart(CartVo cartVo);

    // 특정 회원이 장바구니에 상품이 담겨있는지 확인
    int selectCartTotal(CartVo cartVo);

    // 특정 회원, 특정 상품의 카트 번호 조회
    Long selectCartNumber(CartVo cartVo);

    //장바구니 상품 추가
    void insertCart(CartVo cartVo);

    //장바구니 상품 삭제
    void deleteCart(Long cartNumber);

    //장바구지 상품 수량 추가
    void addCart(CartVo cartVo);

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



// =========================================================================
//    상품 리스트 페이지 전부다 가져오는 쿼리 매퍼
    List<ProductListVo> selectProductList();

//    상품 세부정보 불러오기 + 메인 사진 1장까지
    ProductListVo selectProductDetail(Long productNumber);

//    상품 파일 리스트로 가져오기
    List<ProductFileDto> selectProductFileList(Long productNumber);

//    카테고리 선택시 상품 리스트
    List<ProductListVo> selectProductListForCategory(Long categoryCode2);

//    카테고리 선택시 전체 갯수
    int selectTotalForCategory(Long categoryCode2);

//    즉시 구매
    List<CartVo> selectProductInfoByProductNumber(Long productNumber);
}


