package com.example.mealme.vo;

import com.example.mealme.dto.CompanyDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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
