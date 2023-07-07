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
    private long categoryCode1;
    private long categoryCode2;
    private String productName;
    private String productSeller;
    private int productPrice;
    private String productExplanation;
    private String productRegisterDate;
    private int productInventory;
    private int productCount;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private double averageRating;
    private int totalCount;
    private int userNumber;
    private String userName;
    private String userId;
    private String reviewContent;
    private Date reviewDate;

}