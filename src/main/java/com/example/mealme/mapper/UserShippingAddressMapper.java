package com.example.mealme.mapper;

import com.example.mealme.dto.UserShippingAddressDto;
import com.example.mealme.vo.ShippingVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserShippingAddressMapper {

    public void insert(ShippingVo shippingVo);
}
