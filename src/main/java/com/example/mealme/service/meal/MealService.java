package com.example.mealme.service.meal;

import com.example.mealme.dto.BoardDto;
import com.example.mealme.dto.BoardFileDto;
import com.example.mealme.dto.FoodDto;
import com.example.mealme.mapper.MealMapper;
import com.example.mealme.util.MealUtil;
import com.example.mealme.vo.DayMealVo;
import com.example.mealme.vo.ReadMealVo;
import com.example.mealme.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MealService {
    private final MealMapper mealMapper;


    // 마이페이지 상단 유저 정보
    public UserInfoVo findUserInfo(Long userNumber){
        if (userNumber == null){
            throw new IllegalArgumentException("userNumber 없음!");
        }
        return Optional.ofNullable(mealMapper.userInfo(userNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 유저입니다.");});
    }


    // 식단전체 리스트를 일별리스트로 바꿔 주ㄱl
    public Map<String, List<DayMealVo>> getDayMealList(Long userNumber){
        if (userNumber == null){
            throw new IllegalArgumentException("userNumber 없음 ! ");
        }

        return Optional.ofNullable(MealUtil.getMealList(mealMapper.getMealList(userNumber)))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 유저");});
    }

    public ReadMealVo readMeal(Long boardNumber){
        if (boardNumber == null){
            throw new IllegalArgumentException("boardNumber 없음 !");
        }
        ReadMealVo readMealVo = Optional.ofNullable(mealMapper.getMeal(boardNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 게시글");});

        List<FoodDto> foodList = mealMapper.getFoods(boardNumber);
        double totalKcal = 0;
        for (FoodDto food : foodList) {
            totalKcal += Double.parseDouble(food.getFoodKcal());
        }



        List< BoardFileDto > files = mealMapper.getFiles(boardNumber);

        readMealVo.setFoodList(foodList);
        readMealVo.setMealTotalKcal(totalKcal);
        readMealVo.setFiles(files);

        System.out.println(readMealVo);

        return readMealVo;
    }


    public void registerBoard(BoardDto boardDto){
        if (boardDto == null){
            throw new IllegalArgumentException("입력 게시글 정보 없음 !");
        }
        mealMapper.insertBoard(boardDto);
    }

    public void registerFood(FoodDto foodDto, Long boardNumber) {
        if (foodDto == null) {
            throw new IllegalArgumentException("음식 정보 없음 !");
        }

        String[] foodNameArr = foodDto.getFoodName().split(",");
        String[] foodWeightArr = foodDto.getFoodWeight().split(",");
        String[] foodServingArr = foodDto.getFoodServing().split(",");
        String[] foodKcalArr = foodDto.getFoodKcal().split(",");
        String[] foodCarbohydrateArr = foodDto.getFoodCarbohydrate().split(",");
        String[] foodProteinArr = foodDto.getFoodProtein().split(",");
        String[] foodFatArr = foodDto.getFoodFat().split(",");
        String[] foodSugarsArr = foodDto.getFoodSugars().split(",");
        String[] foodSodiumArr = foodDto.getFoodSodium().split(",");
        String[] foodCholesterolArr = foodDto.getFoodCholesterol().split(",");
        String[] foodFattyAcidArr = foodDto.getFoodFattyAcid().split(",");
        String[] foodTransFatArr = foodDto.getFoodTransFat().split(",");

        foodDto.setBoardNumber(boardNumber);

        for (int i = 0; i < foodNameArr.length; i++) {
            foodDto.setFoodName(foodNameArr[i].trim());
            foodDto.setFoodWeight(foodWeightArr[i].trim().isEmpty() ? "0" : foodWeightArr[i].trim());
            foodDto.setFoodServing(foodServingArr[i].trim().isEmpty() ? "0" : foodServingArr[i].trim());
            foodDto.setFoodKcal(foodKcalArr[i].trim().isEmpty() ? "0" : foodKcalArr[i].trim());
            foodDto.setFoodCarbohydrate(foodCarbohydrateArr[i].trim().isEmpty() ? "0" : foodCarbohydrateArr[i].trim());
            foodDto.setFoodProtein(foodProteinArr[i].trim().isEmpty() ? "0" : foodProteinArr[i].trim());
            foodDto.setFoodFat(foodFatArr[i].trim().isEmpty() ? "0" : foodFatArr[i].trim());
            foodDto.setFoodSugars(foodSugarsArr[i].trim().isEmpty() ? "0" : foodSugarsArr[i].trim());
            foodDto.setFoodSodium(foodSodiumArr[i].trim().isEmpty() ? "0" : foodSodiumArr[i].trim());
            foodDto.setFoodCholesterol(foodCholesterolArr[i].trim().isEmpty() ? "0" : foodCholesterolArr[i].trim());
            foodDto.setFoodFattyAcid(foodFattyAcidArr[i].trim().isEmpty() ? "0" : foodFattyAcidArr[i].trim());
            foodDto.setFoodTransFat(foodTransFatArr[i].trim().isEmpty() ? "0" : foodTransFatArr[i].trim());

            mealMapper.insertFood(foodDto);
        }
    }




}
