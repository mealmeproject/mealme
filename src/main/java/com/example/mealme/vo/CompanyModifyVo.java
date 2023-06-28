package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CompanyModifyVo {
    private long companyNumber;
    private long companyCodeNumber;
    private long companyRegistrationNumber;
    private String companyId;
    private String companyPassword;
    private String companyName;
    private String companyCeoName;
    private String companyBirth;
    private String companyCallNumber;
    private int companyAddressNumber;
    private String companyAddress1;
    private String companyAddress2;
    private String companyAddress3;
    private String companyUrl;
    private String companyEmail;
    private String companyComment;
    private long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
}
