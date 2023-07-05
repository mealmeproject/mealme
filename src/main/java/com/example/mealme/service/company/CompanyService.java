package com.example.mealme.service.company;

import com.example.mealme.mapper.CompanyMapper;
import com.example.mealme.vo.CompanyListVo;
import com.example.mealme.vo.CompanyReviewVo;
import com.example.mealme.vo.ConsultingVo;
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

    public List<CompanyListVo> output(Long companyCodeNumber){
        if(companyCodeNumber == null){
            throw new IllegalArgumentException("companyCodeNumber 없음 !");
        }
        System.out.println(companyCodeNumber);
        List<CompanyListVo> companyList = companyMapper.selectAll(companyCodeNumber);
        System.out.println(companyList);

        return companyList;
    }

    public CompanyListVo showDetail(Long companyNumber){
        return companyMapper.selectDetail(companyNumber);
    }

    public List<CompanyReviewVo> showReview(Long companyNumber){
        return companyMapper.selectReview(companyNumber);
    }

    public void register(ConsultingVo consultingVo) {
        if (consultingVo == null) {
            throw new IllegalArgumentException("신청내용이 없습니다.");
        }
        companyMapper.insert(consultingVo);
    }
}
