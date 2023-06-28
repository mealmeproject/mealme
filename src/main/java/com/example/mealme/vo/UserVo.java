package com.example.mealme.vo;

import com.example.mealme.dto.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserVo {
    private String userId;
    private String userPassword;
    private String userName;
    private String userNickname;
    private String userGender;
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

    public UserDto userVoToUserDto(){
        String userPhoneNumber = phone1 + phone2 + phone3;
        String userBirth = datepicker;
        String userEmail = mail1 + "@" + mail2;
        UserDto userDto = new UserDto(userId, userPassword, userName, userNickname, userGender, userPhoneNumber, userBirth, addressNumber, address, detailAddress, extraAddress, userEmail);
        return userDto;
    }

}
