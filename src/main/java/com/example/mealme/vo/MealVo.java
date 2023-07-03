package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MealVo {
    private Long boardNumber;
    private Long userNumber;
    private String mealDate;
    private String mealTime;
    private Long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private int mealTotalKcal;
}
