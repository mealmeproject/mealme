package com.example.mealme.service.shop;

import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.ProductMapper;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {
    private final ProductMapper productMapper;


    public List<ProductVo> selectAll(Criteria criteria) {

        return productMapper.selectAll(criteria);
    }

        //전체 게시글 수 조회
        @Transactional(readOnly = true)
        public int getTotal(){
            return productMapper.selectTotal();
        }



    public List<ProductVo> selectAllPrice() {

        return productMapper.selectAllPrice();
    }

    public List<ProductVo> selectLowPrice() {

        return productMapper.selectLowPrice();
    }

    public List<ProductVo> selectReviewPrice() {

        return productMapper.selectReviewPrice();
    }

    public ProductVo selectStarRating(String productNumber){
        return productMapper.selectStarRating(productNumber);
    }



    public List<ProductVo> selectList(ProductVo productVo) {

        return productMapper.selectList(productVo);
    }


    public List<CartVo> selectCart(CartVo cartVo) {

        return productMapper.selectCart(cartVo);
    }

    //추가
    public CartVo addCart(CartVo cartVo) {

        if(cartVo == null){
            throw new IllegalArgumentException("장바구니에 상품이 없습니다");
        }

        productMapper.addCart(cartVo);
        return cartVo;
    }

    // 삭제
    public void deleteCart(List<Long> cartNumbers) {

        for(int i=0; i<cartNumbers.size(); i++){
            productMapper.deleteCart(cartNumbers.get(i));
        }
    }

    //장바구니 상품 수량 변경

    public void updateCartPlus(CartVo cartVo){

        productMapper.updateCartPlus(cartVo);
    }

    public void updateCartMinus(CartVo cartVo){

        productMapper.updateCartMinus(cartVo);
    }



    public List<ProductPayVo> insertPay() {

       return productMapper.insertPay();
    }



//
//    public List<OrderInfoVo> orderInfo(){
//
//        return productMapper.orderInfo();
//    }


    public UserDto findUser(Long userNumber){
        return productMapper.selectUser(userNumber);
    }

    public List<CartVo> findCartList(Long userNumber){
        return productMapper.selectCartList(userNumber);
    }
}




