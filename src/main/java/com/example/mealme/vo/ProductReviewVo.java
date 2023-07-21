package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductReviewVo {
        private Long orderNumber;
        private Long userNumber;
        private Long productNumber;
        private Long reviewNumber;
        private String reviewDate;
        private String reviewContent;
        private Long reviewGrade;
        private String orderDate;
        private String productName;
        private String productSeller;
        private String fileName;
        private String fileUploadPath;
        private String fileUuid;
        private Long orderConditionCode;

        private String userNickname;
        private String userId;
        private int starRating;
}
