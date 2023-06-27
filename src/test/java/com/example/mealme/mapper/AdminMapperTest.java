package com.example.mealme.mapper;

import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
public class AdminMapperTest {
    @Autowired
    private AdminMapper adminMapper;

    private UserDto userDto;
    private UserDto userDto2;

    private SearchVo searchVo;

    @BeforeEach
    void setUp(){
       userDto = new UserDto();
        userDto.setUserId("ddd");
        userDto.setUserPassword("1234");
        userDto.setUserName("김바보");
        userDto.setUserNickname("바부");
        userDto.setUserGender("M");
        userDto.setUserBirth("1992-03-22");
        userDto.setUserPhoneNumber("01011112222");
        userDto.setUserAddressNumber(12345);
        userDto.setUserAddress1("경기도 안양시 동안구 학의로 111");
        userDto.setUserAddress2("201동 111호");
        userDto.setUserAddress3("삼성아파트");
        userDto.setUserEmail("aaa123@hanmail.net");

        userDto2 = new UserDto();
        userDto2.setUserId("eee");
        userDto2.setUserPassword("1234");
        userDto2.setUserName("김바보");
        userDto2.setUserNickname("바부");
        userDto2.setUserGender("M");
        userDto2.setUserBirth("1992-03-20");
        userDto2.setUserPhoneNumber("0101123432222");
        userDto2.setUserAddressNumber(123451);
        userDto2.setUserAddress1("경기도 안양시 동안구 학의로 111");
        userDto2.setUserAddress2("201동 111호");
        userDto2.setUserAddress3("삼성아파트");
        userDto2.setUserEmail("eee123@hanmail.net");

//        searchVo = new SearchVo();
//        searchVo.setId("d");

//        userDto.getUserId();
    }


//    @Test
//    void insert(){
//        adminMapper.insert(userDto);
//        assertThat(
//    }

   @Test
   @DisplayName("회원 전체 조회 테스트")
   void selectAll(){
//       List<UserDto> selectAll = adminMapper.selectAll();
       int beforeSize = adminMapper.selectAll().size();
       adminMapper.insert(userDto);
       assertThat(adminMapper.selectAll().size()).isEqualTo(beforeSize+1);
   }

   @Test
    void searchUserList(){
       adminMapper.insert(userDto);
       searchVo = new SearchVo();
       searchVo.setSearchType("id");
       searchVo.setId("ddd");
       searchVo.setKeyword("d");
       List<UserDto> list = adminMapper.searchUser(searchVo);
       System.out.println(list);
//       searchVo.setSearchType("id");
//       searchVo.setKeyword("d");
//       adminMapper.searchUser(searchVo);
//       assertThat(adminMapper.searchUser(searchVo)).isEqualTo(userDto);
   }

    @Test
    void delete(){
        adminMapper.insert(userDto);
//        adminMapper.insert(userDto2);
        adminMapper.delete("56");
        assertThat(adminMapper.selectAll().size()).isEqualTo(3);
    }
}
