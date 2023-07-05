package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Clob;

@Component
@Data
@NoArgsConstructor
public class ConsultingVo {

    private long consultingNumber;
    private long companyNumber;
    private long userNumber;
    private String consultingComment;
    private String consultingDate;
    private String companyName;
    private String userNickname;
    private long consultingRequestNumber;

}
