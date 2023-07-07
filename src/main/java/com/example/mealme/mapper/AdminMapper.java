package com.example.mealme.mapper;

import com.example.mealme.dto.*;
import com.example.mealme.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
//    전체 회원 조회
    List<UserDto> selectAll();

//    검색 회원 조회
    List<UserDto> searchUser(@Param("searchVo") SearchVo searchVo , @Param("criteria") Criteria criteria);



//    회원 입력
    public void insert(UserDto userDto);



//    회원 삭제
    Long delete(String userNumber);

    //    전체 게시글 수 조회
    public int selectTotal(@Param("searchVo")SearchVo searchVo);

    List<UserDto> selectAll(Criteria criteria);

//    대분류 조회
    List<ProductCategory1Dto> selectCategory();

//    중분류 조회
    List<ProductCategory2Dto> selectCategory2(Long categoryCode1);

//    상품 등록
    void insertProduct(ProductDto productDto);

//    상품 조회
    List<ProductVo> productListSelect(@Param("searchProductVo") SearchProductVo searchProductVo, @Param("criteria") Criteria criteria);
    //    전체 상품 수 조회
    public int selectProductTotal(@Param("searchProductVo") SearchProductVo searchProductVo);

//    상품 단일 조회
List<ProductVo> selectProduct(Long productNumber);





//    상품 등록Vo

    void registProduct(ProductVo productVo);


    //    검색 기업 조회
    List<CompanyDto> searchCompany(@Param("searchVo") SearchVo searchVo , @Param("criteria") Criteria criteria);

    //    전체 기업 수 조회
    public int selectCompanyTotal(@Param("searchVo")SearchVo searchVo);

    //    주문 검색 조회
    List<OrderVo> selectOrderList(@Param("searchProductVo") SearchProductVo searchProductVo, @Param("criteria") Criteria criteria);
    //    전체 상품 수 조회
    public int selectOrderTotal(@Param("searchProductVo") SearchProductVo searchProductVo);
//     주문 상태 변경
    void modifyCondition(@Param("orderNumber") Long orderNumber, @Param("orderConditionCode") Long orderConditionCode );

}
