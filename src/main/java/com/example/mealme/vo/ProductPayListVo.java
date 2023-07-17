package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductPayListVo {
        long orderNumber;
        long userNumber;
        long productNumber;
        long orderCount;
        String orderDate;
        long orderConditionCode;
        String conditionCodeName;
        String productName;
        String productExplanation;
        String productSeller;
        long productPrice;
        String fileName;
        String fileUploadPath;
}
