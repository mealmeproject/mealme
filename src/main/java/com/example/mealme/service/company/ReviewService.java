package com.example.mealme.service.company;

import com.example.mealme.mapper.ReviewMapper;
import com.example.mealme.vo.ConsultingReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public ConsultingReviewVo findConsultingInfo(Long consultingRequestNumber){
        ConsultingReviewVo consultingReviewVo = reviewMapper.selectConsultingInfo(consultingRequestNumber);
        return consultingReviewVo;
    }

    public void register(ConsultingReviewVo consultingReviewVo){
        if(consultingReviewVo.getReviewContent() == null || consultingReviewVo.getReviewGrade() == 0){
            throw new IllegalArgumentException("게시물 정보가 없습니다.");
        }
        reviewMapper.insertConsultingReview(consultingReviewVo);
    }

    public List<ConsultingReviewVo> findConsultingList(Long userNumber){
        List<ConsultingReviewVo> consultingList = reviewMapper.selectConsultingList(userNumber);
        System.out.println("consultingList");
        return consultingList;
    }

    public ConsultingReviewVo selectConsultingReviewInfo(Long reviewNumber){
        ConsultingReviewVo consultingReviewVo = reviewMapper.selectConsultingReviewInfo(reviewNumber);
        return consultingReviewVo;
    }

    public void consultingReviewUpdate(ConsultingReviewVo consultingReviewVo){
        if(consultingReviewVo == null){
            throw new IllegalArgumentException("컨설팅 리뷰의 수정 정보가 없습니다.");
        }
        reviewMapper.updateConsultingReview(consultingReviewVo);
    }

    public void removeConsultingReview(Long reviewNumber){
        if(reviewNumber == null){
            throw new IllegalArgumentException("존재하지 않는 컨설팅 리뷰입니다.");
        }
        reviewMapper.deleteConsultingReview(reviewNumber);
    }
}
