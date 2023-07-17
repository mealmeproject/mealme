package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductPayListVo {
        private long orderNumber;
        private long userNumber;
        private long productNumber;
        private long orderCount;
        private String orderDate;
        private long orderConditionCode;
        private String conditionCodeName;
        private String productName;
        private String productExplanation;
        private String productSeller;
        private long productPrice;
        private String fileName;
        private String fileUploadPath;
}
