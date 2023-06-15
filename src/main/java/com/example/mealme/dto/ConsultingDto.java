package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Clob;

@Component
@Data
@NoArgsConstructor
public class ConsultingDto {
    private long consultingNumber;
    private long companyNumber;
    private long userNumber;
    private Clob consultingContent;
    private String consultingDate;
}
