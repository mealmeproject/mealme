package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AdminDto {
    private long adminNumber;
    private String adminId;
    private String adminPassword;
    private String adminName;
    private String adminEmail;
}
