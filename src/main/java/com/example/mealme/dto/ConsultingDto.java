package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ConsultingDto {
    private long consultingNumber;
    private long consultingRequestNumber;
    private long companyNumber;
    private long userNumber;
    private String consultingComment;
    private String consultingDate;
}
