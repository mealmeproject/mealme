package com.example.mealme.mapper;

import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.Criteria;
import com.example.mealme.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
//    전체 회원 조회
    List<UserDto> selectAll();
//    검색 회원 조회
    List<UserDto> searchUser(SearchVo searchVo);
//    회원 입력
    public void insert(UserDto userDto);

    List<UserDto> selectListPage(Criteria criteria);
}
