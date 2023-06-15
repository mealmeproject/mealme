package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserFileDto {
    private long fileNumber;
    private long userNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
}
