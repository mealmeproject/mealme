package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.LongFunction;


@Component
@Data
@NoArgsConstructor
public class CompanyReviewVo {
   private Long reviewNumber;
   private Long consultingNumber;
   private Long userNumber;
   private Long companyNumber;
   private String reviewDate;
   private String reviewContent;
   private Long reviewGrade;
   private String userNickname;
   private Long fileNumber;
   private String fileName;
   private String fileUploadPath;
   private String fileUuid;
}
