package com.example.mealme.controller.meal;

import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.DayMealVo;
import com.example.mealme.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meal/*")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest req, Model model){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        if (userNumber == null) {
            return "user/login";
        }

        UserInfoVo userInfoVo = mealService.findUserInfo(userNumber).userInfoSetUp();
        System.out.println(userInfoVo);

        // 식단 가져오기
        Map<String, List<DayMealVo>> mealList = new HashMap<>();
        mealList = mealService.getDayMealList(userNumber);

        System.out.println("컨트롤러에서 보내는 mealList " + mealList);
        // 모델에 담아서 보내기
        model.addAttribute("userInfo", userInfoVo);
        model.addAttribute("mealList", mealList);

        return "meal/myPage";
    }
}
