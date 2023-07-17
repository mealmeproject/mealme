package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ProductPayVo {
    private String productName;
    private long productNumber;
    private long orderNumber;
    private long userNumber;
    private long orderConditionCode;
    private long shippingAddressNumber;
    private long orderCount;
    private String orderCDate;
    private String productSeller;
    private String productExplanation;
    private String productRegisterDate;
    private long productInventory;
    private long productPrice;
    private long categoryCode1;
    private long categoryCode2;
    private String userNickname;
    private String address;
    private String userName;
    private int addressNumber;
    private String mail1;
    private String mail2;
    private String userId;
    private String phone1;
    private String phone2;
    private String phone3;
    private String detailAddress;
    private String extraAddress;
}
