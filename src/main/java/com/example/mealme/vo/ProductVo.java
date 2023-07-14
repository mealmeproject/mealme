package com.example.mealme.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductVo {
    private long productNumber;
    private String productName;
    private String productExplanation;
    private long productPrice;
    private String productSeller;
    private String categoryName;
    private String categoryName1;
    private String categoryName2;
    private long categoryCode1;
    private long categoryCode2;
    private String productRegisterDate;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private long fileNumber;
}
