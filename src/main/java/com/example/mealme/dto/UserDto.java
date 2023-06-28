package com.example.mealme.dto;

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
}
