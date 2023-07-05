package com.example.mealme.controller.company;

import com.example.mealme.service.company.CompanyService;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.CompanyListVo;
import com.example.mealme.vo.CompanyReviewVo;
import com.example.mealme.vo.ConsultingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
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
        System.out.println(companyNumber + "==========================================");
        CompanyListVo detailedList = companyService.showDetail(companyNumber);
        List<CompanyReviewVo> selectReviewList = companyService.showReview(companyNumber);
        model.addAttribute("companyinfo", detailedList);
        System.out.println(detailedList);
        model.addAttribute("companyReviewList", selectReviewList);
        System.out.println(companyNumber);
        return "company/detailedHospital";
    }

    @GetMapping("/settingThePeriod")
    public String settingThePeriod(@RequestParam("companyNumber")Long companyNumber, Model model){
        System.out.println("작성 페이지 companyNumber" + companyNumber);
        model.addAttribute("companyNumber", companyNumber);
        return "company/settingThePeriod";
    }

    @PostMapping("/settingThePeriod")
    public RedirectView settingThePeriod(ConsultingVo consultingVo, HttpServletRequest req, Model model){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        System.out.println("===============================================================");
        System.out.println(consultingVo.getCompanyNumber());
        System.out.println(consultingVo);
        consultingVo.setUserNumber(userNumber);
        companyService.register(consultingVo);
        model.addAttribute("companyNumber", consultingVo.getCompanyNumber());
        System.out.println(consultingVo + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String path = "/company/detailedHospital?companyNumber=" + consultingVo.getCompanyNumber();
        return new RedirectView(path);
    }



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


