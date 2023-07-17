package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Clob;

@Component
@Data
@NoArgsConstructor
public class ConsultingRequestDto {
    private long consultingRequestNumber;
    private long userNumber;
    private long companyNumber;
    private String consultingRequestComment;
    private String consultingRequestFirstDate;
    private String consultingRequestLastDate;
    private String consultingRequestDate;
    private String consultingCondition;
}
