package com.example.mealme.mapper;

import com.example.mealme.vo.CompanyListVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;
    private CompanyListVo companyListVo;

    @BeforeEach
    void setUp(){
        companyListVo = new CompanyListVo();
        companyListVo.setCompanyNumber(2L);
        companyListVo.setCompanyName("삼성서울병원");
        companyListVo.setCompanyCeoName("이재용");
        companyListVo.setCompanyCallNumber("1588-5858");
        companyListVo.setCompanyAddress1("경기도 성남시 분당구 정자일로");
        companyListVo.setReviewGrade(4.2);
        companyListVo.setCompanyCodeNumber(100L);
    }


    @Test
    void selectAll() {
        companyMapper.selectAll(100L);
    }
}