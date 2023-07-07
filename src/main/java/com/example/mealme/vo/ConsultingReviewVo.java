package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ConsultingReviewVo {
    private long consultingRequestNumber;
    private long companyNumber;
    private long userNumber;
    private String consultingComment;
    private String fileName;
    private String fileUploadPath;
    private String consultingRequestDate;
    private long reviewNumber;
    private String reviewDate;
    private String reviewContent;
    private long reviewGrade;
}
