package com.example.mealme.mapper;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.service.shop.ShopService;
import com.example.mealme.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Transactional //롤백
public class ProductMapperTest {


    @Autowired
    private ProductMapper productMapper;
    private ProductVo productVo;

    @BeforeEach
    void setUp() {
        productVo = new ProductVo();
        productVo.setProductNumber(1);
        productVo.setProductExplanation("aaa");
        productVo.setProductPrice(1500);
        productVo.setProductInventory(100);
        productVo.setProductName("김철수");
        productVo.setProductSeller("박원순");
        productVo.setCategoryCode1(1);
        productVo.setCategoryCode2(2);
        productVo.setFileUploadPath("templates/img");
        productVo.setFileName("aaa");
        productVo.setFileUuid("asss");
        productVo.setProductCount(10);

    }

    @Test
    @DisplayName("유저 회원가입 테스트")
    void selectAll() {

        productMapper.selectAll();

       productVo.getProductPrice();
       productVo.getProductPrice();
       productVo.getProductPrice();
    }




}

