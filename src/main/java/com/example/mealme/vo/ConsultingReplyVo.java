package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ConsultingReplyVo {

    private long replyNumber;
    private long consultingNumber;
    private long userNumber;
    private String replyContent;
    private String replyDate;
    private String userNickname;


}
