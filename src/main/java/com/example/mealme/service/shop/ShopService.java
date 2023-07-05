package com.example.mealme.service.shop;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.mapper.ProductMapper;
import com.example.mealme.vo.CartVo;
import com.example.mealme.vo.ProductVo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {
    private final ProductMapper productMapper;

    public List<ProductVo> selectAll(){

        return productMapper.selectAll();
    }

    public List<ProductVo> selectAllPrice(){

        return productMapper.selectAllPrice();
    }

    public  List<ProductVo> selectLowPrice(){

        return productMapper.selectLowPrice();
    }

    public  List<ProductVo> selectReviewPrice(){

        return productMapper.selectReviewPrice();
    }

    public ProductVo selectOne(String productNumber) {
        if (productNumber == null) {
            return null;
        }

        return productMapper.selectOne(Long.parseLong(productNumber));
    }


    public List<CartVo> selectCart(String userNumber){

        return productMapper.selectCart(Long.parseLong(userNumber));
    }

//    장바구니에 상품 추가
    public void addCart(CartVo cartVo){
        if(cartVo == null){
            throw new IllegalArgumentException("장바구니에 담긴 상품이 없습니다.");
        }
        productMapper.addCart(cartVo);
    }

}


