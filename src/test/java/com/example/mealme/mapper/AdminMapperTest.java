package com.example.mealme.mapper;

import com.example.mealme.dto.ProductCategory1Dto;
import com.example.mealme.dto.ProductCategory2Dto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.Criteria;
import com.example.mealme.vo.ProductVo;
import com.example.mealme.vo.SearchProductVo;
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

    private ProductCategory2Dto productCategory2Dto;
    private ProductCategory1Dto productCategory1Dto;

    private ProductVo productVo;
    private SearchProductVo searchProductVo;

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
//       List<UserDto> list = adminMapper.searchUser(searchVo);
//       System.out.println(list);
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

    @Test
    void selectCategory2(){
      productCategory1Dto = new ProductCategory1Dto();
      productCategory2Dto = new ProductCategory2Dto();

        productCategory1Dto.setCategoryCode1(4);
        productCategory2Dto.setCategoryCode1(4);
        productCategory2Dto.setCategoryCode2(8);
        productCategory2Dto.setCategoryName("치킨");



    }
     @Test
     void productListSelect(){
        productVo = new ProductVo();
        searchProductVo = new SearchProductVo();
         Criteria criteria = new Criteria();
       ProductVo productVo2 = new ProductVo();

        productVo.setProductName("닭가슴살");
        productVo.setProductPrice(12340);
        productVo.setProductSeller("하림");
        productVo.setCategoryName("단백질");

        productVo.setProductRegisterDate("2023-05-02");

        productVo2.setProductName("저칼로리");
        productVo2.setProductPrice(12340);
        productVo2.setProductSeller("한끼통살");
        productVo2.setProductRegisterDate("2023-06-02");


        adminMapper.registProduct(productVo);
         adminMapper.registProduct(productVo2);

//        searchProductVo.setProductType("");
//        searchProductVo.setKeyword("");
//        searchProductVo.setProductType("");
        searchProductVo.setStartDate("2023-04-02");
        searchProductVo.setEndDate("2023-07-02");

        searchProductVo.setProductType("단백질");
        searchProductVo.setSearchType("name");
         searchProductVo.setKeyword("");

         System.out.println(productVo2);
         System.out.println(searchProductVo);
         System.out.println(criteria);

         System.out.println(adminMapper.productListSelect(searchProductVo,criteria));
//        assertThat(adminMapper.productListSelect(searchProductVo,criteria)).hasSize(1);
        log.info(String.valueOf(adminMapper.productListSelect(searchProductVo,criteria)));

     }


}



