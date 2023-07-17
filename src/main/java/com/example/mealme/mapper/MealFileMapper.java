package com.example.mealme.mapper;

import com.example.mealme.dto.BoardFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MealFileMapper {

    public void delete(Long boardNumber);

    public void insert(BoardFileDto boardFileDto);

    public List<BoardFileDto> selectList(Long boardNumber);

    public List<BoardFileDto> selectOldList();

}
