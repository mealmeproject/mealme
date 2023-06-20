package com.example.mealme.service.user;

import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUserId("aaa");
        userDto.setUserPassword("1234");
        userDto.setUserName("김바보");
        userDto.setUserNickname("바부");
        userDto.setUserGender("M");
        userDto.setUserPhoneNumber("01011112222");
        userDto.setUserAddressNumber(12345);
        userDto.setUserAddress1("경기도 안양시 동안구 학의로 111");
        userDto.setUserAddress2("201동 111호");
        userDto.setUserAddress3("삼성아파트");
        userDto.setUserEmail("aaa123@hanmail.net");
    }


    @Test
    @DisplayName("회원 등록")
    void register() {
        doNothing().when(userMapper).insert(any(UserDto.class));

        userService.register(userDto);

        verify(userMapper, times(1)).insert(any(UserDto.class));
    }

    @Test
    @DisplayName("회원번호조회 : 존재하지 않는 회원 예외 검사")
    void findUserNumberException(){
        doReturn(null).when(userMapper).selectUserNumber(any(String.class), any(String.class));

        Assertions.assertThatThrownBy(() -> userService.findUserNumber("a", "a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지");
    }

    @Test
    @DisplayName("회원 번호 조회")
    void findUserNumber() {
        doReturn(1L).when(userMapper).selectUserNumber(any(String.class), any(String.class));

        Long userNumber = userService.findUserNumber("test", "12345");

        Assertions.assertThat(userNumber).isEqualTo(1L);

    }
}