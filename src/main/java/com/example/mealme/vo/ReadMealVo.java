package com.example.mealme.vo;

import com.example.mealme.dto.BoardFileDto;
import com.example.mealme.dto.FoodDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ReadMealVo {
    private Long boardNumber;
    private Long mealCodeNumber;
    private Long userNumber;
    private String boardTitle;
    private String boardContent;
    private String mealTime;
    private int mealTotalKcal;
    private List<FoodDto> foodList;
    private List<BoardFileDto> files;
}
