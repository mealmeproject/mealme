package com.example.mealme.service.company;

import com.example.mealme.mapper.CompanyMapper;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.LongFunction;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyMapper companyMapper;

    public List<CompanyListVo> output(Long companyCodeNumber, CriteriaCompany criteriaCompany){
        if(companyCodeNumber == null){
            throw new IllegalArgumentException("companyCodeNumber 없음 !");
        }
        System.out.println(companyCodeNumber);
        System.out.println(criteriaCompany);
        List<CompanyListVo> companyList = companyMapper.selectAll(companyCodeNumber, criteriaCompany.getPage(), criteriaCompany.getAmount());
        System.out.println(companyList);

        return companyList;
    }

    public CompanyListVo showDetail(Long companyNumber){
        return companyMapper.selectDetail(companyNumber);
    }

    public List<CompanyReviewVo> showReview(Long companyNumber, CriteriaCompany criteriaCompany){
        return companyMapper.selectReview(companyNumber, criteriaCompany);
    }

    public void register(ConsultingVo consultingVo) {
        if (consultingVo == null) {
            throw new IllegalArgumentException("신청내용이 없습니다.");
        }
        companyMapper.insert(consultingVo);
    }

    //    전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal(Long companyCodeNumber){
        return companyMapper.selectTotal(companyCodeNumber);
    }

    @Transactional(readOnly = true)
    public int getReview(Long companyNumber){
        return companyMapper.selectReviewList(companyNumber);
    }
}
