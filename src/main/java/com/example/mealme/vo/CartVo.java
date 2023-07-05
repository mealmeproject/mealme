package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class CartVo {
    private long cartNumber;
    private long userNumber;
    private String userName;
    private long productNumber;
    private String productName;
    private long productPrice;
    private String productExplanation;
    private int cartCount;
    private double totalCount;
    private long fileNumber;
    private String fileUploadPath;
    private String fileUuid;

}
