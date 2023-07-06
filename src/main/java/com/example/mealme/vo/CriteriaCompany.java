package com.example.mealme.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor 
public class CriteriaCompany {
    private int page; // 현재 페이지
    private int amount; // 한 페이지 당 게시물 수


    public CriteriaCompany() {
        this(1,5);

    }

}
