package com.example.mealme.mapper;

import com.example.mealme.dto.ProductFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductFileMapper {
    public void insert(ProductFileDto productFileDto);
    public void delete(Long productNumber);
    public List<ProductFileDto> selectList(Long productNumber);
    public int productCheck(Long productNumber);
    List<ProductFileDto> selectOldList();
}
