package com.example.mealme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CompanyDto {
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

    public CompanyDto(long companyCodeNumber, long companyRegistrationNumber, String companyId, String companyPassword, String companyName, String companyCeoName, String companyCallNumber, String companyBirth, int companyAddressNumber, String companyAddress1, String companyAddress2, String companyAddress3, String companyEmail) {
        this.companyCodeNumber = companyCodeNumber;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.companyId = companyId;
        this.companyPassword = companyPassword;
        this.companyName = companyName;
        this.companyCeoName = companyCeoName;
        this.companyBirth = companyBirth;
        this.companyCallNumber = companyCallNumber;
        this.companyAddressNumber = companyAddressNumber;
        this.companyAddress1 = companyAddress1;
        this.companyAddress2 = companyAddress2;
        this.companyAddress3 = companyAddress3;
        this.companyEmail = companyEmail;
    }
}
