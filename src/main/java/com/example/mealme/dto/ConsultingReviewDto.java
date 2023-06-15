package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ConsultingReviewDto {
    private long reviewNumber;
    private long consultingNumber;
    private long userNumber;
    private long companyNumber;
    private String reviewDate;
    private String reviewContent;
    private String reciewGrade;
}
