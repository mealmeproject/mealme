package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Clob;

@Component
@Data
@NoArgsConstructor
public class CancelDto {
    private long cancelNumber;
    private long orderNumber;
    private String cancelReason;
    private String cancelStatus;
}
