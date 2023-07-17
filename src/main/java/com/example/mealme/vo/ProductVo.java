package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
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
    private int productInventory;
    private int productCount;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private long fileNumber;
    private double averageRating;
    private int starRating;
    private int totalCount;
    private int userNumber;
    private String userName;
    private String userId;
    private String reviewContent;
    private Date reviewDate;
}
