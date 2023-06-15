package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CompanyCodeDto {
    private long companyCodeNumber;
    private String companyCodeName;
}
