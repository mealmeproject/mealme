package com.example.mealme.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SearchCompanyVo {

    private String keyword;
    private String searchType;
    private int productType;
}
