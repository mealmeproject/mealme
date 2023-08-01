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

// 현재 날짜 받기
        LocalDate currentDate = LocalDate.now();

// 회원 생일 받은 거 저장하기
        String birthDateString = userBirth;

// 회원 생일 문자열을 LocalDate 객체로 변환
        LocalDate birthDate = LocalDate.parse(birthDateString, DateTimeFormatter.ISO_DATE);

// 생년월일과 현재 날짜 사이 계산
        Period age = Period.between(birthDate, currentDate);

// 계산한 년,월,일 객체에 담기
        int years = age.getYears();
        int months = age.getMonths();
        int days = age.getDays();

// 나이 날짜 출력해보기
        System.out.println("Your age is " + years + " years, " + months + " months, and " + days + " days.");


        int recommendKcal = 0;
        if (userGender.equals("M")){
            recommendKcal = (int) ((66.47+(13.75*userWeight)+(5*userHeight) - (6.76 * (double)years))*1.2);

        }else if(userGender.equals("F")){
            recommendKcal = (int) ((655.1+(9.56*userWeight)+(1.85*userHeight)-(4.68*(double)years))*1.2);
        }

        if (userWeight == 0 || userHeight == 0){
            recommendVo.setRecommendKcal(0);
            recommendVo.setRecommendCarbohydrate(0);
            recommendVo.setRecommendProtein(0);
            recommendVo.setRecommendFat(0);
            recommendVo.setUserComment(userComment);
        }else{
            recommendVo.setRecommendKcal(recommendKcal);
            recommendVo.setRecommendCarbohydrate((int)Math.round(recommendKcal * 0.5/4));
            recommendVo.setRecommendProtein((int)Math.round(recommendKcal * 0.3/4));
            recommendVo.setRecommendFat((int)Math.round(recommendKcal * 0.2/9));
            recommendVo.setUserComment(userComment);
        }

        return recommendVo;
    }
}
