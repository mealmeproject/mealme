package com.example.mealme.mapper;

import com.example.mealme.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
//    컨설팅
    public ConsultingReviewVo selectConsultingInfo(Long consultingRequestNumber);
    public void insertConsultingReview(ConsultingReviewVo consultingReviewVo);
    public List<ConsultingReviewVo> selectConsultingList(@Param("userNumber")Long userNumber, @Param("criteria") CriteriaCompany criteriaCompany);
    public ConsultingReviewVo selectConsultingReviewInfo(Long reviewNumber);
    public void updateConsultingReview(ConsultingReviewVo consultingReviewVo);
    public void deleteConsultingReview(Long reviewNumber);
//    컨설팅 구매 리스트
    public List<ConsultingPayVo> selectOrderConsultingList(@Param("userNumber") Long userNumber, @Param("criteria") Criteria criteria);
//    리뷰 작성 시 컨설팅컨디션 2로 변경
    public void updateConsultingCondition(Long consultingRequestNumber);
//    상품 구매 리스트
    public List<ProductPayListVo> selectOrderProductList(@Param("userNumber") Long userNumber, @Param("criteria") Criteria criteria);
//    구매 내역 리스트 개수
    public int orderProductListCount();
    public int orderConsultingListCount();
    public ProductReviewVo selectProductInfo(Long orderNumber);
    public void insertProductReview(ProductReviewVo productReviewVo);
    public void updateOrderCondition(Long orderNumber);
    public List<ProductReviewVo> selectProductReviewList(Long userNumber);
    public ProductReviewVo selectProductReviewInfo(Long reviewNumber);
    public void updateProductReview(ProductReviewVo productReviewVo);
    public void deleteProductReview(Long reviewNumber);
//    리뷰 리스트 전체 갯수
    public int selectConsultingReviewCount(Long userNumber);

}
