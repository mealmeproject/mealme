package com.example.mealme.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//                CR.USER_NUMBER, CR.CONSULTING_REQUEST_NUMBER, CM.COMPANY_NUMBER, CF.FILE_NAME, CF.FILE_UPLOAD_PATH, CM.COMPANY_NAME,
//                        CR.CONSULTING_REQUEST_COMMENT, CR.CONSULTING_REQUEST_DATE, CM.COMPANY_CEO_NAME, CR.CONSULTING_CONDITION

@Component
@Data
@NoArgsConstructor
public class ConsultingPayVo {
        long userNumber;
        long consultingRequestNumber;
        long companyNumber;
        String fileName;
        String fileUploadPath;
        String companyName;
        String consultingRequestComment;
        String consultingRequestDate;
        String companyCeoName;
        String consultingCondition;
}
