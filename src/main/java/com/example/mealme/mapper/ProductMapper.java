package com.example.mealme.mapper;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.vo.CartVo;
import com.example.mealme.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.io.CharArrayWriter;
import java.util.List;

@Mapper
public interface ProductMapper {



    //전체 조회(별점,총 개수,상품 이미지)
    List<ProductVo> selectAll();

    //전체 음식 조회
    List<ProductVo> selectAllPrice();

    //낮은 가격 순으로 조회
    List<ProductVo> selectLowPrice();

    //높은 가격 순으로 조회
    List<ProductVo> selectReviewPrice();

    //상품 상세정보 조회
    ProductVo selectOne(Long productNumber);

    //장바구니 상품 조회
    List<CartVo> selectCart(Long userNumber);

    //장바구니 상품 추가
    public void addCart(CartVo cartVo);

    }


