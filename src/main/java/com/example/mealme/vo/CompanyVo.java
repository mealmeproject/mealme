package com.example.mealme.vo;

import com.example.mealme.dto.CompanyDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CompanyVo {
    private String companyId;
    private String companyPassword;
    private String companyName;
    private long companyRegistrationNumber;
    private String companyCeoName;
    private String companyType;
    private String datepicker;
    private String phone1;
    private String phone2;
    private String phone3;
    private String mail1;
    private String mail2;
    private int addressNumber;
    private String address;
    private String detailAddress;
    private String extraAddress;

    public CompanyDto companyVoToCompanyDto(){
        Long companyCodeNumber = 0l;

        if (companyType.equals("hospital")){
            companyCodeNumber = 100l;
        }else if (companyType.equals("trainer")){
            companyCodeNumber = 200l;
        }else if (companyType.equals("dietitian")){
            companyCodeNumber = 300l;
        }

        String companyCallNumber = phone1 + phone2 + phone3;
        String companyBirth = datepicker;
        String companyEmail = mail1 + "@" + mail2;

        CompanyDto companyDto = new CompanyDto(companyCodeNumber, companyRegistrationNumber, companyId, companyPassword, companyName, companyCeoName, companyCallNumber, companyBirth, addressNumber , address, detailAddress, extraAddress, companyEmail);
        return companyDto;
    };
}
