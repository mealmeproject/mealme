package com.example.mealme.util;

import com.example.mealme.dto.UserDto;
import com.example.mealme.vo.RecommendVo;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ChartUtil {


    public static RecommendVo calculate(String userGender, double userHeight, double userWeight, String userBirth, String userComment){



        RecommendVo recommendVo = new RecommendVo();
        System.out.println(userBirth);
        UserDto userDto = new UserDto();

// Get the current date
        LocalDate currentDate = LocalDate.now();

// Replace the following line with the birth date retrieved from the database
        String birthDateString = userBirth;

// Parse the birth date string into a LocalDate object
        LocalDate birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ISO_DATE);

// Calculate the difference between the birth date and current date
        Period age = Period.between(birthDate, currentDate);

// Extract the years, months, and days from the age
        int years = age.getYears();
        int months = age.getMonths();
        int days = age.getDays();

// Print the age
        System.out.println("Your age is " + years + " years, " + months + " months, and " + days + " days.");


        int recommendKcal = 0;
        if (userGender.equals("M")){
            recommendKcal = (int) ((66.47+(13.75*userWeight)+(5*userHeight) - (6.76 * (double)years))*1.2);

        }else if(userGender.equals("F")){
            recommendKcal = (int) ((655.1+(9.56*userWeight)+(1.85*userHeight)-(4.68*(double)years))*1.2);
        }
        recommendVo.setRecommendKcal(recommendKcal);
//        int recommendCarbohydrateValue = (int) Math.round(recommendKcal * 0.5 / 4);
//        System.out.println(recommendCarbohydrateValue);
        recommendVo.setRecommendCarbohydrate((int)Math.round(recommendKcal * 0.5/4));
        recommendVo.setRecommendProtein((int)Math.round(recommendKcal * 0.3/4));
        recommendVo.setRecommendFat((int)Math.round(recommendKcal * 0.2/9));
        recommendVo.setUserComment(userComment);
//        recommendVo.setUserComment(userDto.getUserComment());  이거 아님 이걸 넣으면 null값이 뜸
//        userDto는

//        나의 목표 넣기
//        recommendVo.setRecommendUserComment
        return recommendVo;
    }
}
