package com.example.mealme.controller.company;

import com.example.mealme.service.company.CompanyService;
import com.example.mealme.service.company.ReviewService;
import com.example.mealme.service.meal.MealService;
import com.example.mealme.vo.ConsultingReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/company/*")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final ReviewService reviewService;

    @GetMapping("/largeCategory")
    public void largeCategory(){}

    @GetMapping("/hospitalCategory")
    public void hospitalCategory(){}

    @GetMapping("/detailedHospital")
    public void detailedHospital(){}

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

    @GetMapping("/consultingReview")
    public String consultingReview(HttpServletRequest req, Model model) {
//        Long userNumber = (long)req.getSession().getAttribute("userNumber");
        Long consultingRequestNumber = 5L;
        ConsultingReviewVo consultingReviewVo = reviewService.findConsultingInfo(consultingRequestNumber);
        System.out.println("%%%%%%");
        System.out.println(consultingReviewVo);

        model.addAttribute("consultingReviewVo", consultingReviewVo);
        return "company/consultingReview";
    }

    @PostMapping("/consultingReview")
    public RedirectView consultingReviewWrite(ConsultingReviewVo consultingReviewVo){
        reviewService.register(consultingReviewVo);
        return new RedirectView("/company/consultingReviewShow");
    }
//
//
    @GetMapping("/consultingReviewShow")
    public void consultingReviewShow() {
    }

}
