package com.example.mealme.vo;

import com.example.mealme.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserModifyVo {
    private long userNumber;
    private String userId;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userGender;
    private String userBirth;
    private String userPhoneNumber;
    private String phone1;
    private String phone2;
    private String phone3;
    private int userAddressNumber;
    private String userAddress1;
    private String userAddress2;
    private String userAddress3;
    private String userEmail;
    private String userEmail1;
    private String userEmail2;
    private String userJoinDate;
    private String userComment;
    private double userHeight;
    private double userWeight;
    private int userGrade;
    private long fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private String getFile;


    public UserModifyVo userModifyVoSetUp() {
        String phone1 = userPhoneNumber.substring(0, 3);
        String phone2 = userPhoneNumber.substring(3, 7);
        String phone3 = userPhoneNumber.substring(7, 11);

        String[] array = userEmail.split("@");
        userEmail1 = array[0];
        userEmail2 = array[1];

        String settingFile = "";
        if (fileName == null){
            settingFile = "/img/sampleProfile.png";
        }else {
            settingFile = "/upload/" + fileUploadPath + "/" + fileUuid + "_" + fileName;
        }

        System.out.println(settingFile);

        UserModifyVo userModifyVo = new UserModifyVo();

        userModifyVo.setUserNumber(userNumber);
        userModifyVo.setUserId(userId);
        userModifyVo.setUserPassword(userPassword);
        userModifyVo.setUserName(userName);
        userModifyVo.setUserNickname(userNickname);
        userModifyVo.setUserGender(userGender);
        userModifyVo.setUserBirth(userBirth);
        userModifyVo.setUserAddressNumber(userAddressNumber);
        userModifyVo.setUserAddress1(userAddress1);
        userModifyVo.setUserAddress2(userAddress2);
        userModifyVo.setUserAddress3(userAddress3);
        userModifyVo.setUserJoinDate(userJoinDate);
        userModifyVo.setUserComment(userComment);
        userModifyVo.setUserHeight(userHeight);
        userModifyVo.setUserWeight(userWeight);
        userModifyVo.setUserGrade(userGrade);
        userModifyVo.setFileNumber(fileNumber);
        userModifyVo.setFileName(fileName);
        userModifyVo.setFileUploadPath(fileUploadPath);
        userModifyVo.setFileUuid(fileUuid);
        userModifyVo.setGetFile(settingFile);

        userModifyVo.setPhone1(phone1);
        userModifyVo.setPhone2(phone2);
        userModifyVo.setPhone3(phone3);

        userModifyVo.setUserEmail1(userEmail1);
        userModifyVo.setUserEmail2(userEmail2);

        return userModifyVo;
    }


    public UserDto userModifyVoToUserDto() {
        String settingPhoneNumber = phone1 + phone2 + phone3;
        String userEmail = userEmail1 + "@" + userEmail2;
        UserDto userDto = new UserDto();

        userDto.setUserName(userName);
        userDto.setUserPassword(userPassword);
        userDto.setUserGender(userGender);
        userDto.setUserBirth(userBirth);
        userDto.setUserPhoneNumber(settingPhoneNumber);
        userDto.setUserAddressNumber(userAddressNumber);
        userDto.setUserAddress1(userAddress1);
        userDto.setUserAddress2(userAddress2);
        userDto.setUserAddress3(userAddress3);
        userDto.setUserEmail(userEmail);
        userDto.setUserHeight(userHeight);
        userDto.setUserWeight(userWeight);

        return userDto;
    }
}
