package com.example.mealme.controller.company;

import com.example.mealme.service.company.CompanyService;
import com.example.mealme.service.company.ReviewService;
import com.example.mealme.vo.ConsultingReviewVo;
import com.example.mealme.vo.ConsultingVo;
import com.example.mealme.vo.CriteriaCompany;
import com.example.mealme.vo.PageListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/companies/*")
@RequiredArgsConstructor
public class CompanyRestController {
    public final CompanyService companyService;
    public final ReviewService reviewService;

    @PostMapping("/settingThePeriods")
    public String insert(@RequestBody ConsultingVo consultingVo, HttpServletRequest req){
//        System.out.println(consultingVo);
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        consultingVo.setUserNumber(userNumber);
        companyService.register(consultingVo);
        return "settingThePeriod";
    }

    @GetMapping("/consultingReviewListData")
    public Map<String, Object> consultingReviewListData(HttpServletRequest req, int page) {
        System.out.println("%%+++++++++++++++++++++++++++++RestController");
      Long userNumber = (long)req.getSession().getAttribute("userNumber");
//        Long userNumber = 1L;
        CriteriaCompany criteriaCompany = new CriteriaCompany(page, 5);
        List<ConsultingReviewVo> consultingReviewList = reviewService.findConsultingList(userNumber, criteriaCompany);



//        Criteria 자체의 amount를 5로 변경해서 다시 해보기...  안되네...ㅠ page를 받는게 안 돼
//        Criteria criteria = new Criteria(page, 5);
//        PageVo pageVo = new PageVo(criteria, reviewService.findConsultingReviewCount(userNumber));

        PageListVo pageVo = new PageListVo(criteriaCompany, reviewService.findConsultingReviewCount(userNumber));

        System.out.println("##########");
        System.out.println(consultingReviewList);
        Map<String, Object> reviewList = new HashMap<>();
        reviewList.put("consultingReviewList", consultingReviewList);
        reviewList.put("pageVo", pageVo);
        System.out.println(new PageListVo(criteriaCompany,reviewService.findConsultingReviewCount(userNumber)));
        System.out.println(pageVo);

        return reviewList;
    }





}

