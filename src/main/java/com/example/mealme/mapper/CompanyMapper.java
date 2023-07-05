package com.example.mealme.mapper;

import com.example.mealme.vo.CompanyListVo;
import com.example.mealme.vo.CompanyReviewVo;
import com.example.mealme.vo.ConsultingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {
    public List<CompanyListVo> selectAll(Long companyCodeNumber);
    public CompanyListVo selectDetail(Long companyNumber);
    public List<CompanyReviewVo> selectReview(Long companyNumber);
    public void insert(ConsultingVo consultingVo);
}
