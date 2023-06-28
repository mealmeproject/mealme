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
    List<UserDto> searchUser(@Param("searchVo") SearchVo searchVo , @Param("criteria") Criteria criteria);
//    회원 입력
    public void insert(UserDto userDto);



//    회원 삭제
    Long delete(String userNumber);

    //    전체 게시글 수 조회
    public int selectTotal(@Param("searchVo")SearchVo searchVo);

    List<UserDto> selectAll(Criteria criteria);
}
