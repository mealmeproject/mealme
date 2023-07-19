package com.example.mealme.service.shop;

import com.example.mealme.dto.OrderDto;
import com.example.mealme.dto.ProductFileDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.CartMapper;
import com.example.mealme.mapper.OrderMapper;
import com.example.mealme.mapper.ProductMapper;
import com.example.mealme.mapper.UserShippingAddressMapper;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final UserShippingAddressMapper userShippingAddressMapper;
    private final CartMapper cartMapper;

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



    public List<ProductReviewVo> findReviewList(Long productNumber) {

        return productMapper.selectReviewList(productNumber);
    }


    public List<CartVo> selectCart(CartVo cartVo) {

        return productMapper.selectCart(cartVo);
    }

    // 특정 회원이 장바구니에 상품이 담겨있는지 확인
    public int findCartCount(CartVo cartVo){
        return productMapper.selectCartTotal(cartVo);
    }

    //추가
    public CartVo registerCart(CartVo cartVo) {

        if(cartVo == null){
            throw new IllegalArgumentException("장바구니에 상품이 없습니다");
        }

        productMapper.insertCart(cartVo);
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


    // 장바구니 로직
    public void processCart(CartVo cartVo){
        int cartTotal = productMapper.selectCartTotal(cartVo);

        if(cartTotal == 0){
            productMapper.insertCart(cartVo);
        }else{
            Long cartNumber = productMapper.selectCartNumber(cartVo);
            cartVo.setCartNumber(cartNumber);
            productMapper.addCart(cartVo);
        }
    }



    public List<ProductPayVo> insertPay() {

       return productMapper.insertPay();
    }

//    결제 프로세스
    public List<OrderDto> paymentProcess(ShippingVo shippingVo){
        if (shippingVo == null) {
            throw new IllegalArgumentException("상품 결제 정보 누락");
        }
//        조건 추가해야함 이미 배송지 정보가 존재하는 경우 insert를 하지 않음
        userShippingAddressMapper.insert(shippingVo);

        shippingVo.getOrderDtoList().stream().map(dto -> {
            dto.setShippingAddressNumber(shippingVo.getShippingAddressNumber());
            return dto;
        }).forEach(orderMapper::insert);

//        결제 완료 후 장바구니에서 상품 삭제
        shippingVo.getOrderDtoList().stream().forEach(orderDto -> {
            cartMapper.delete(orderDto.getUserNumber(), orderDto.getProductNumber());
        });

//        결제 완료 후 final페이지에 뿌려줄 데이터 반환
        return shippingVo.getOrderDtoList();
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

// ================================================================================

//     쇼핑몰 상품 리스트 ( 사진까지 )
    public List<ProductListVo> findProductList(){
        return productMapper.selectProductList();
    }

//    상품 세부정보
    public ProductListVo findProductDetail(Long productNumber){
        if (productNumber== null){
            throw new IllegalArgumentException("찾으시는 상품 번호가 없습니다.");
        }
        return productMapper.selectProductDetail(productNumber);
    }

//    상품 파일 리스트
    public List<ProductFileDto> findProductFiles(Long productNumber){
        if (productNumber == null) {
            throw new IllegalArgumentException("찾으시는 상품 번호가 없습니다.");
        }
        return productMapper.selectProductFileList(productNumber);
    }


    //     쇼핑몰 상품 리스트 카테고리2로 검색 ( 사진까지 )
    public List<ProductListVo> findProductListForCategory(Long categoryNumber2){
        return productMapper.selectProductList();
    }

    // getTOtal 상품리스트 카테고리2 검색
    public int getTotalForCategory(Long categoryNumber2){
        return productMapper.selectTotal();
    }



}




