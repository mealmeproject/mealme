package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ConsultingPayVo {
        private long userNumber;
        private long consultingRequestNumber;
        private long companyNumber;
        private String fileName;
        private String fileUploadPath;
        private String companyName;
        private String consultingRequestComment;
        private String consultingRequestDate;
        private String companyCeoName;
        private String consultingCondition;
        private String fileUuid;
}
