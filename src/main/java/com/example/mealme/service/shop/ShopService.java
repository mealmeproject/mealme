package com.example.mealme.service.shop;

import com.example.mealme.dto.ProductDto;
import com.example.mealme.mapper.ProductMapper;
import com.example.mealme.vo.ProductVo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopService {
    private final ProductMapper productMapper;

    public List<ProductVo> selectAll(){

        return productMapper.selectAll();
    }

    public List<ProductDto> foodCategory(String categoryName) {

        return productMapper.foodCategory(categoryName);
    }


    public List<ProductVo> selectAllPrice(){

        return productMapper.selectAllPrice();
    }

    public  List<ProductVo> selectLowPrice(){

        return productMapper.selectLowPrice();
    }

    public  List<ProductVo> selectReviewPrice(){

        return productMapper.selectReviewPrice();
    }





}
