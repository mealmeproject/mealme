package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductListVo {
        private Long productNumber;
        private Long categoryCode1;
        private Long categoryCode2;
        private String productName;
        private String productSeller;
        private int productPrice;
        private String productExplanation;
        private String productRegisterDate;
        private int productInventory;
        private Long fileNumber;
        private String fileName;
        private String fileUploadPath;
        private String fileUuid;
        private Double avgGrade;
}
