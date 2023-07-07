package com.example.mealme.vo;

import lombok.Data;

import org.springframework.stereotype.Component;

@Component
@Data
public class SearchProductVo {

    private String keyword;

    private Long keyword2;

    private String searchType;

    private String productType;

    private String startDate;

    private String endDate;


}
