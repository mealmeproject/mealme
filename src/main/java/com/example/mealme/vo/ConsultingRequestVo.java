package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ConsultingRequestVo {

    private long consultingRequestNumber;
    private long userNumber;
    private long companyNumber;
    private String consultingRequestComment;
    private String consultingRequestFirstDate;
    private String consultingRequestLastDate;
    private String consultingRequestDate;
    private String consultingRequestCondition;
    private String userNickname;

}
