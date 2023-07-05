package com.example.mealme.controller.company;

import com.example.mealme.dto.ConsultingRequestDto;
import com.example.mealme.mapper.ConsultingMapper;
import com.example.mealme.mapper.ConsultingReplyMapper;
import com.example.mealme.service.company.ConsultingReplyService;
import com.example.mealme.service.company.ConsultingService;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/company/*")
@RequiredArgsConstructor
public class CompanyController {
    private final ConsultingMapper consultingMapper;
    private final SendConsultingVo sendConsultingVo;
    private final ConsultingService consultingService; // private을 final로 변경하고, 필드 주입을 생성자 주입으로 변경
    private final ConsultingReplyMapper consultingReplyMapper;
    private final ConsultingReplyVo consultingReplyVo;
    private final ConsultingReplyService consultingReplyService;
    private final PageVo pageVo;
    private final Criteria criteria;


    @GetMapping("/largeCategory")
    public void largeCategory() {
    }

    @GetMapping("/hospitalCategory")
    public void hospitalCategory() {
    }

    @GetMapping("/detailedHospital")
    public void detailedHospital() {
    }

    @GetMapping("/settingThePeriod")
    public void settingThePeriod() {
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
}