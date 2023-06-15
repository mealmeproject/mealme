package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MealFileDto {
    private long fileNumber;
    private long boardNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
}
