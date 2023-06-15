package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class LikeDto {
    private long likeNumber;
    private long productNumber;
    private long userNumber;
}
