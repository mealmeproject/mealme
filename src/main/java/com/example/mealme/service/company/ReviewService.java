package com.example.mealme.service.company;

import com.example.mealme.mapper.ReviewMapper;
import com.example.mealme.vo.ConsultingReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewMapper reviewMapper;

    public ConsultingReviewVo findConsultingInfo(long consultingRequestNumber){
        ConsultingReviewVo consultingReviewVo = reviewMapper.selectConsultingInfo(consultingRequestNumber);
        return consultingReviewVo;
    }

    public void register(ConsultingReviewVo consultingReviewVo){

        if(consultingReviewVo.getReviewContent() == null || consultingReviewVo.getReviewGrade() == 0){
            throw new IllegalArgumentException("게시물 정보가 없습니다.");
        }
        reviewMapper.insertConsultingReview(consultingReviewVo);
    }
}
