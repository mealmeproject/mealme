package com.example.mealme.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper {
    public void delete(@Param("userNumber") Long userNumber, @Param("productNumber") Long productNumber);
}
