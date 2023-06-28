package com.example.mealme.mapper;

import com.example.mealme.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Slf4j
@Transactional

class ChartMapperTest {


//    @Autowired
//    private UserMapper userMapper;
//    private ChartMapper chartMapper;
//    private UserDto userDto;


//    @BeforeEach
//    void setUp(){
//
////        USER_NUMBER,USER_ID, USER_PASSWORD, USER_NAME, USER_NICKNAME, USER_GENDER, USER_PHONE_NUMBER,
////                USER_ADDRESS_NUMBER, USER_ADDRESS1, USER_ADDRESS2, USER_ADDRESS3, USER_EMAIL, USER_BIRTH,
////                USER_JOIN_DATE, USER_COMMENT, USER_HEIGHT, USER_WEIGHT, USER_GRADE
//        userDto = new UserDto();
//        userDto.setUserId("testId");
//        userDto.setUserPassword("1234");
//        userDto.setUserName("레몬");
//        userDto.setUserNickname("소다");
//        userDto.setUserGender("M");
//        userDto.setUserPhoneNumber("01011111111");
//        userDto.setUserAddressNumber(1234);
//        userDto.setUserAddress1("테스트");
//        userDto.setUserAddress2("테스트");
//        userDto.setUserAddress3("테스트");
//        userDto.setUserEmail("test@email.com");
//        userDto.setUserBirth("2002-01-01");
//        userDto.setUserJoinDate("2023-06-01");
//        userDto.setUserComment("안녕");
//        userDto.setUserHeight(170);
//        userDto.setUserWeight(50);
//        userDto.setUserGrade(1);
//    }


//    @Test
//    void selectUserInfo() {
////            assertThat(chartMapper.selectUserInfo(userDto.getUserNumber()))
////                    .isEqualTo(userDto.getUserNumber());
//    }
}