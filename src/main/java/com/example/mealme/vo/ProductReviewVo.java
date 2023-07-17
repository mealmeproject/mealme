package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductReviewVo {
        long orderNumber;
        long userNumber;
        long productNumber;
        long reviewNumber;
        String reviewDate;
        String reviewContent;
        long reviewGrade;
        String orderDate;
        String productName;
        String productSeller;
        String fileName;
        String fileUploadPath;
        long orderConditionCode;
}
