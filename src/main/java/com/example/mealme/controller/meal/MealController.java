package com.example.mealme.controller.meal;

import com.example.mealme.dto.BoardDto;
import com.example.mealme.dto.FoodDto;
import com.example.mealme.service.meal.MealFileService;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.DayMealVo;
import com.example.mealme.vo.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meal/*")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    private final MealFileService mealFileService;

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest req, Model model) {
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

    @GetMapping("/mealWrite")
    public void mealWrite() {
    }

    @PostMapping("/mealWrite")
    public RedirectView mealWrite(HttpServletRequest req, BoardDto boardDto,
                                  RedirectAttributes redirectAttributes,
                                  @RequestParam("boardFiles") List<MultipartFile> files,
                                  FoodDto foodDto) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        System.out.println("boardDto=================================" + boardDto);
        boardDto.setUserNumber(userNumber);
        mealService.registerBoard(boardDto);

        System.out.println(files);
        redirectAttributes.addFlashAttribute("boardNumber", boardDto.getBoardNumber());
        if(files != null){
            try {
                mealFileService.registerAndSaveFiles(files, boardDto.getBoardNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(foodDto);
        mealService.registerFood(foodDto, boardDto.getBoardNumber());

        return new RedirectView ("/meal/myPage");
    }

    @GetMapping("mealDelete")
    public RedirectView mealDelete(@RequestParam("boardNumber") Long boardNumber){
        mealService.DeleteBoard(boardNumber);
        return new RedirectView("/meal/myPage");
    }
}
