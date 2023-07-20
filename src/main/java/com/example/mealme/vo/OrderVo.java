package com.example.mealme.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class OrderVo {
    private String orderDate;
    private Long orderNumber;
    private String userName;
    private String productSeller;
    private String productName;
    private Long productPrice;
    private int orderCount;
    private String conditionCodeName;
    private Long orderConditionCode;

    private Long userNumber;
    private Long productNumber;
    private Long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
}
