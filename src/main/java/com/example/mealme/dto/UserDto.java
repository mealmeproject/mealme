package com.example.mealme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserDto {
    private long userNumber;
    private String userId;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userGender;
    private String userBirth;
    private String userPhoneNumber;
    private int userAddressNumber;
    private String userAddress1;
    private String userAddress2;
    private String userAddress3;
    private String userEmail;
    private String userJoinDate;
    private String userComment;
    private double userHeight;
    private double userWeight;
    private int userGrade;

    public UserDto(String userId, String userPassword, String userName, String userNickname, String userGender, String userPhoneNumber, String userBirth, int userAddressNumber, String userAddress1, String userAddress2, String userAddress3, String userEmail) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userGender = userGender;
        this.userPhoneNumber = userPhoneNumber;
        this.userBirth = userBirth;
        this.userAddressNumber = userAddressNumber;
        this.userAddress1 = userAddress1;
        this.userAddress2 = userAddress2;
        this.userAddress3 = userAddress3;
        this.userEmail = userEmail;
    }
}
