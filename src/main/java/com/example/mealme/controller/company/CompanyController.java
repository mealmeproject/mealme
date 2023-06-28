package com.example.mealme.controller.company;

import com.example.mealme.service.company.CompanyService;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.CompanyListVo;
import com.example.mealme.vo.CompanyReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/company/*")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("/largeCategory")
    public void largeCategory(){}

    @GetMapping("/hospitalCategory")
    public String hospitalCategory(@RequestParam("companyCodeNumber")Long companyCodeNumber, Model model){
        String companyCodeName = null;
        if(companyCodeNumber == 100){
            companyCodeName = "병원";
        } else if(companyCodeNumber == 200){
            companyCodeName = "트레이너";
        } else if(companyCodeNumber == 300){
            companyCodeName = "영양사";
        }
        List<CompanyListVo> returnList = companyService.output(companyCodeNumber);
        model.addAttribute("companies", returnList);
        model.addAttribute("companyCodeName", companyCodeName);
        return "company/hospitalCategory";
    }

    @GetMapping("/detailedHospital")
    public String detailedHospital(@RequestParam("companyNumber")Long companyNumber, Model model){
        CompanyListVo detailedList = companyService.showDetail(companyNumber);
        List<CompanyReviewVo> selectReviewList = companyService.showReview(companyNumber);
        model.addAttribute("companyinfo", detailedList);
        model.addAttribute("companyReviewList", selectReviewList);
        model.addAttribute("companyNumber", companyNumber);
        return "company/detailedHospital";
    }

    @GetMapping("/settingThePeriod")
    public void settingThePeriod(){}


    @GetMapping("/ConsultingList")
    public void ConsultingList() {

    }

    @GetMapping("/ConsultingList2")
    public void ConsultingList2() {

    }

    @GetMapping("/SendConsulting")
    public void SendConsulitng() {

    }

    @GetMapping("/WhyRefund")
    public void WhyRefund() {

    }

    @GetMapping("/SendReadList")
    public void SendReadList() {
    }

    @GetMapping("/SendReadList2")
    public void SendReadList2() {
    }

}


