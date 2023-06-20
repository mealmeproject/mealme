package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserShippingAddressDto {
    private long shippingAddressNumber;
    private long userNumber;
    private int addressNumber;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingAddress3;
}
