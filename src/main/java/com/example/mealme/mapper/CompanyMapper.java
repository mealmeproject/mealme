package com.example.mealme.mapper;

import com.example.mealme.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CompanyMapper {
    public List<CompanyListVo> selectAll(Long companyCodeNumber, int page, int amount);
    public CompanyListVo selectDetail(Long companyNumber);
    public List<CompanyReviewVo> selectReview(Long companyNumber,@Param("criteria") CriteriaCompany criteriaCompany);
    public void insert(ConsultingVo consultingVo);
    public int selectTotal(Long companyCodeNumber);
    public int selectReviewList(Long companyNumber);
}
