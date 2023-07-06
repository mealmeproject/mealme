package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
public class ConsultingVo {
  private Long companyNumber;
  private Long userNumber;
  private Long consultingRequestNumber;
  private String consultingRequestComment;
  private String consultingRequestFirstDate;
  private String consultingRequestLastDate;
  private String consultingRequestDate;
}
