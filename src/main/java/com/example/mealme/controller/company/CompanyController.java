package com.example.mealme.controller.company;

import com.example.mealme.dto.ConsultingRequestDto;
import com.example.mealme.mapper.ConsultingMapper;
import com.example.mealme.mapper.ConsultingReplyMapper;
import com.example.mealme.service.company.ConsultingReplyService;
import com.example.mealme.service.company.ConsultingService;
import com.example.mealme.vo.*;
import com.example.mealme.service.company.CompanyService;
import com.example.mealme.service.company.ReviewService;
import com.example.mealme.service.meal.MealService;

import com.example.mealme.vo.CompanyListVo;
import com.example.mealme.vo.CompanyReviewVo;
import com.example.mealme.vo.ConsultingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private final ConsultingMapper consultingMapper;
    private final SendConsultingVo sendConsultingVo;
    private final CompanyService companyService;
    private final ConsultingService consultingService; // private을 final로 변경하고, 필드 주입을 생성자 주입으로 변경
    private final ConsultingReplyMapper consultingReplyMapper;
    private final ConsultingReplyVo consultingReplyVo;
    private final ConsultingReplyService consultingReplyService;
    private final PageVo pageVo;
    private final Criteria criteria;

    private final ReviewService reviewService;

    @GetMapping("/largeCategory")
    public void largeCategory() {
    }



    //    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ아래가 정현
    @PostMapping("/SendConsulting1")
    public RedirectView sendConsulting(SendConsultingVo sendConsultingVo) {

        System.out.println(sendConsultingVo + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        consultingService.register(sendConsultingVo);
        return new RedirectView("/company/AfterConsulting");
    }


    @GetMapping("/SendConsulting")
    public String sendConsulting(@RequestParam("consultingRequestNumber") Long consultingRequestNumber ,Model model) {
        System.out.println("==============================================================");
        System.out.println(consultingRequestNumber);

        // 컨설팅 신청 데이터 가져오기
        ConsultingRequestVo consultingRequestVo = consultingService.selectRequestOne(consultingRequestNumber);

        // 컨설팅 신청 데이터에 있는 기간으로 유저의 먹은 식단 가져오기
        Map<String, List<DayMealVo>> mealList = new HashMap<>();
        mealList = consultingService.getDayMealList(consultingRequestVo);

        // 식단 리스트 보냄 + 컨설팅 데이터도
        model.addAttribute("consultingRequest", consultingRequestVo);
        model.addAttribute("mealList", mealList);
        return "company/SendConsulting";
    }

    @GetMapping("/hospitalCategory")
    public String hospitalCategory(@RequestParam("companyCodeNumber")Long companyCodeNumber, Model model, CriteriaCompany criteriaCompany){
        String companyCodeName = null;
        if(companyCodeNumber == 100){
            companyCodeName = "병원";
        } else if(companyCodeNumber == 200){
            companyCodeName = "트레이너";
        } else if(companyCodeNumber == 300){
            companyCodeName = "영양사";
        }
        List<CompanyListVo> returnList = companyService.output(companyCodeNumber, criteriaCompany);
        model.addAttribute("pageInfo", new PageListVo(criteriaCompany, companyService.getTotal(companyCodeNumber)));
        model.addAttribute("companies", returnList);
        model.addAttribute("companyCodeName", companyCodeName);
        model.addAttribute("companyCodeNumber", companyCodeNumber);
        return "company/hospitalCategory";
    }

    @GetMapping("/detailedHospital")
    public String detailedHospital(@RequestParam("companyNumber")Long companyNumber, Model model, CriteriaCompany criteriaCompany){
        System.out.println(companyNumber + "==========================================");
        CompanyListVo detailedList = companyService.showDetail(companyNumber);
        List<CompanyReviewVo> selectReviewList = companyService.showReview(companyNumber, criteriaCompany);
        model.addAttribute("pageInfo", new PageListVo(criteriaCompany, companyService.getReview(companyNumber)));
        model.addAttribute("companyNumber", companyNumber);
        model.addAttribute("companyinfo", detailedList);
        System.out.println(detailedList);
        model.addAttribute("companyReviewList", selectReviewList);
        System.out.println(companyNumber);
        return "company/detailedHospital";
    }


    @GetMapping("/settingThePeriod")
    public String settingThePeriod(@RequestParam("companyNumber")Long companyNumber, Model model, HttpServletRequest req) {
        System.out.println("작성 페이지 companyNumber" + companyNumber);
//        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        model.addAttribute("companyNumber", companyNumber);
        if (req.getSession().getAttribute("userNumber") == null) {
            return "user/login";
        } else {
            return "company/settingThePeriod";
        }
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
    public String consultingList(ConsultingRequestDto consultingRequestDto, Model model, HttpServletRequest req) {

        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");

        List<ConsultingRequestDto> consultingRequests = consultingService.findAllConsultingRequests(companyNumber);

        System.out.println(consultingRequests + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        model.addAttribute("consultingRequests", consultingRequests);
        return "/company/ConsultingList";
    }

//    @PostMapping("consultingList2")
//    public RedirectView consultingList2(Model model) {
//        List<ConsultingRequestDto> consultingRequests = consultingService.findAllConsultingRequests();
//        model.addAttribute("consultingRequests", consultingRequests);
//        return new RedirectView( "/company/SendConsulting");
//    }



    @GetMapping("/WhyRefund")
    public void WhyRefund() {

    }


    @GetMapping("/SendReadList")
    public String sendReadList(Criteria criteria, Model model) {
        List<SendConsultingVo> consultingList = consultingService.findAll(criteria); // 새로운 메서드 사용


        model.addAttribute("pageInfo", new PageVo(criteria, consultingService.getTotal()));
        model.addAttribute("consultingList", consultingList);
        return "company/SendReadList";
    }


    @PostMapping("/SendReadList")
    public RedirectView SendReadList(ConsultingReplyVo consultingReplyVo) {

        consultingReplyService.register(consultingReplyVo);
        System.out.println(consultingReplyVo + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return new RedirectView("/company/AfterConsulting");
    }


    @GetMapping("/SendReadList2")
    public void SendReadList2() {

    }


    @GetMapping("/AfterConsulting")
    public String AfterConsulting(Model model,Criteria criteria) {
        // 조회하려는 consultingNumber 설정 (예시: 1L)
        Long consultingNumber = 1L;
        SendConsultingVo consulting = consultingMapper.select(consultingNumber);

        // 조회하려는 userNumber 설정 (예시: 1L)
        Long userNumber = 1L;

        // 전체 컨설팅 목록을 조회합니다.
        List<SendConsultingVo> consultingList = consultingService.findAll(criteria);

        model.addAttribute("consulting", consulting);
        model.addAttribute("consultingNumber", consultingNumber);
        model.addAttribute("userNumber", userNumber);
        model.addAttribute("consultingList", consultingList);

                  Long replyNumber=1L;
       ConsultingReplyVo replyNo = consultingReplyMapper.select(replyNumber);

     List<ConsultingReplyVo> consultingReplyList=consultingReplyMapper.selectAll();

      model.addAttribute("replyNo", replyNo);
       model.addAttribute("consultingReplyList" , consultingReplyList);



        return "company/AfterConsulting";
    }
    @GetMapping("/consultingReview")
    public String consultingReview(HttpServletRequest req, Model model) {
//        Long userNumber = (long)req.getSession().getAttribute("userNumber");
        Long consultingRequestNumber = 7L;
        ConsultingReviewVo consultingReviewVo = reviewService.findConsultingInfo(consultingRequestNumber);
        System.out.println("%%%%%%");
        System.out.println(consultingReviewVo);

        model.addAttribute("consultingReviewVo", consultingReviewVo);
        return "company/consultingReview";
    }

    @PostMapping("/consultingReview")
    public RedirectView consultingReviewWrite(ConsultingReviewVo consultingReviewVo){
        reviewService.register(consultingReviewVo);
        return new RedirectView("/company/consultingReviewList");
    }

    @GetMapping("/consultingReviewList")
    public void consultingReviewList(){}

    @ResponseBody
    @GetMapping("/consultingReviewListData")
    public Map<String, Object> consultingReviewListData(HttpServletRequest req) {
//      Long userNumber = (long)req.getSession().getAttribute("userNumber");
        Long userNumber = 1L;
        List<ConsultingReviewVo> consultingReviewList = reviewService.findConsultingList(userNumber);

        System.out.println("##########");
        System.out.println(consultingReviewList);
        Map<String, Object> reviewList = new HashMap<>();
        reviewList.put("consultingReviewList", consultingReviewList);
        return reviewList;
    }

//    컨설팅 결제 내역 페이지
    @GetMapping("/consultingPayInfo")
    public void consultingPayInfo(){

    }

    @GetMapping("/consultingReviewModify")
    public String consultingReviewModify(long reviewNumber, Model model){
        ConsultingReviewVo consultingReviewVo = reviewService.selectConsultingReviewInfo(reviewNumber);
        model.addAttribute("consultingReviewVo", consultingReviewVo);
        System.out.println("#####수정페이지 정보 받아오기#####");
        System.out.println(consultingReviewVo);
        return "company/consultingReviewModify";
    }

    @PostMapping("/consultingReviewModify")
    public RedirectView consultingReviewModify(ConsultingReviewVo consultingReviewVo){
        reviewService.consultingReviewUpdate(consultingReviewVo);
        System.out.println("&&&&&&&&&&&&&&&&&&&"+consultingReviewVo.toString());
        return new RedirectView("/company/consultingReviewList");
    }

    @GetMapping("/consultingReviewRemove")
    public RedirectView consultingReviewRemove(Long reviewNumber){
        reviewService.removeConsultingReview(reviewNumber);
        return new RedirectView("/company/consultingReviewList");
    }


}


