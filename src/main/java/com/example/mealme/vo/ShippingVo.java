package com.example.mealme.vo;

import com.example.mealme.dto.OrderDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ShippingVo {
    private Long shippingAddressNumber;
    private Long userNumber;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingAddress3;
    private Integer addressNumber;

    private String shoppingName;
    private List<OrderDto> orderDtoList;
}
