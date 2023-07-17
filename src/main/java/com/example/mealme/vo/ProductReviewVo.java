package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductReviewVo {
        private long orderNumber;
        private long userNumber;
        private long productNumber;
        private long reviewNumber;
        private String reviewDate;
        private String reviewContent;
        private long reviewGrade;
        private String orderDate;
        private String productName;
        private String productSeller;
        private String fileName;
        private String fileUploadPath;
        private long orderConditionCode;
}
