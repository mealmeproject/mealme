package com.example.mealme.service.company;

import com.example.mealme.mapper.CompanyMapper;
import com.example.mealme.vo.CompanyListVo;
import com.example.mealme.vo.CompanyReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {
    private final CompanyMapper companyMapper;

    public List<CompanyListVo> output(Long companyCodeNumber){
        return companyMapper.selectAll(companyCodeNumber);
    }

    public CompanyListVo showDetail(Long companyNumber){
        return companyMapper.selectDetail(companyNumber);
    }

    public List<CompanyReviewVo> showReview(Long companyNumber){
        return companyMapper.selectReview(companyNumber);
    }
}
