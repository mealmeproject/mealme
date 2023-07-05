package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ConsultingReplyDto {
    private long replyNumber;
    private long consultingNumber;
    private long userNumber;
    private String replyContent;
    private String replyDate;
}
