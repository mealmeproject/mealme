package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class BoardDto {
    private long boardNumber;
    private long mealCodeNumber;
    private long userNumber;
    private String boardTitle;
    private String mealTime;
    private String boardContent;
    private String boardRegisterDate;
    private String boardUpdateDate;
}
