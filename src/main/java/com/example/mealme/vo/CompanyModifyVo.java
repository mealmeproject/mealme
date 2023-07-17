package com.example.mealme.vo;

import com.example.mealme.dto.CompanyDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CompanyModifyVo {
    private long companyNumber;
    private long companyCodeNumber;
    private String companyRegistrationNumber;
    private String companyId;
    private String companyPassword;
    private String companyName;
    private String companyCeoName;
    private String companyBirth;
    private String companyCallNumber;
    private String phone1;
    private String phone2;
    private String phone3;
    private int companyAddressNumber;
    private String companyAddress1;
    private String companyAddress2;
    private String companyAddress3;
    private String companyUrl;
    private String companyEmail;
    private String companyEmail1;
    private String companyEmail2;
    private String companyJoinDate;
    private String companyComment;
    private int companyStatus;
    private String companyInformation;
    private long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private String getFile;


    public CompanyModifyVo companyModifyVoSetUp() {

        System.out.println(companyCallNumber.substring(0, 2) + "=============================================");
        if (companyCallNumber.substring(0,2).equals("02")){
            phone1 = companyCallNumber.substring(0, 2);
            phone2 = companyCallNumber.substring(2, 5);
            phone3 = companyCallNumber.substring(5, 9);
        } else {
            String getPhone1 = companyCallNumber.substring(0, 3);
            String getPhone2 = companyCallNumber.substring(3, 6);
            String getPhone3 = companyCallNumber.substring(6, 9);
        }

        System.out.println(phone1 + phone2 + phone3);

        String[] array = companyEmail.split("@");
        companyEmail1 = array[0];
        companyEmail2 = array[1];

        String settingFile = "";
        if (fileName == null){
            settingFile = "/img/sampleProfile.png";
        }else {
            settingFile = "/upload/" + fileUploadPath + "/" + fileUuid + "_" + fileName;
        }

        CompanyModifyVo companyModifyVo = new CompanyModifyVo();

        companyModifyVo.setCompanyNumber(companyNumber);
        companyModifyVo.setCompanyId(companyId);
        companyModifyVo.setCompanyPassword(companyPassword);
        companyModifyVo.setCompanyName(companyName);
        companyModifyVo.setCompanyCeoName(companyCeoName);
        companyModifyVo.setCompanyBirth(companyBirth);
        companyModifyVo.setCompanyAddressNumber(companyAddressNumber);
        companyModifyVo.setCompanyAddress1(companyAddress1);
        companyModifyVo.setCompanyAddress2(companyAddress2);
        companyModifyVo.setCompanyAddress3(companyAddress3);
        companyModifyVo.setCompanyUrl(companyUrl);
        companyModifyVo.setCompanyComment(companyComment);
        companyModifyVo.setCompanyInformation(companyInformation);
        companyModifyVo.setCompanyJoinDate(companyJoinDate);
        companyModifyVo.setFileNumber(fileNumber);
        companyModifyVo.setFileName(fileName);
        companyModifyVo.setFileUploadPath(fileUploadPath);
        companyModifyVo.setFileUuid(fileUuid);


        companyModifyVo.setPhone1(phone1);
        companyModifyVo.setPhone2(phone2);
        companyModifyVo.setPhone3(phone3);

        companyModifyVo.setCompanyEmail1(companyEmail1);
        companyModifyVo.setCompanyEmail2(companyEmail2);

        companyModifyVo.setGetFile(settingFile);

        return companyModifyVo;
    }

    public CompanyDto companyVoToDto(){
        String companyCallNumber = phone1 + phone2 + phone3;
        String companyEmail = companyEmail1 + "@" + companyEmail2;

        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyCodeNumber(companyCodeNumber);
        companyDto.setCompanyPassword(companyPassword);
        companyDto.setCompanyBirth(companyBirth);
        companyDto.setCompanyCallNumber(companyCallNumber);
        companyDto.setCompanyEmail(companyEmail);
        companyDto.setCompanyAddressNumber(companyAddressNumber);
        companyDto.setCompanyUrl(companyUrl);
        companyDto.setCompanyAddress1(companyAddress1);
        companyDto.setCompanyAddress2(companyAddress2);
        companyDto.setCompanyAddress3(companyAddress3);
        companyDto.setCompanyInformation(companyInformation);
        System.out.println(companyDto);
        return companyDto;



    }

}
