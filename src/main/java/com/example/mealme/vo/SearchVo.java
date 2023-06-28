package com.example.mealme.vo;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SearchVo {
    private String keyword;
    private String searchType;
    private String name;
    private String id;
    private String email;
}

