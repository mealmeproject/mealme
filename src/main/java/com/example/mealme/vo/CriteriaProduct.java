package com.example.mealme.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class CriteriaProduct {
    private Long categoryCode1;
    private Long categoryCode2;

    public CriteriaProduct() {

    }

    public Long getCategoryCode1() {
        return categoryCode1;
    }

    public void setCategoryCode1(Long categoryCode1) {
        this.categoryCode1 = categoryCode1;
    }

    public Long getCategoryCode2() {
        return categoryCode2;
    }

    public void setCategoryCode2(Long categoryCode2) {
        this.categoryCode2 = categoryCode2;
    }
}

