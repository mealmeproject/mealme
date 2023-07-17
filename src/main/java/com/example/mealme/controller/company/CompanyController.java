package com.example.mealme.controller.company;

import com.example.mealme.dto.ConsultingDto;
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
import org.apache.ibatis.annotations.Param;
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
    @GetMapping("/SendConsulting")
    public String sendConsulting(@RequestParam("consultingRequestNumber") Long consultingRequestNumber ,Model model, Criteria criteria) {
        System.out.println("==============================================================");
        System.out.println(consultingRequestNumber);

        // 컨설팅 신청 데이터 가져오기
        ConsultingRequestVo consultingRequestVo = consultingService.selectRequestOne(consultingRequestNumber);

        // 컨설팅 신청 데이터에 있는 기간으로 유저의 먹은 식단 가져오기
        Map<String, List<DayMealVo>> mealList = new HashMap<>();
        mealList = consultingService.getDayMealList(consultingRequestVo);

        // 컨설팅 (consultingRequestNumber에 맞는 TBL_CONSULTING에 있는 모든 리스트를 가져옴)
        List<SendConsultingVo> consultingList = consultingService.findAll(consultingRequestNumber, criteria);

        // 식단 리스트 보냄 + 컨설팅 데이터도
        model.addAttribute("consultingList", consultingList);
        model.addAttribute("consultingRequest", consultingRequestVo);
        model.addAttribute("mealList", mealList);
        return "company/SendConsulting";
    }

    @PostMapping("/SendConsulting")
    public RedirectView sendConsulting(@ModelAttribute("sendConsultingVo") SendConsultingVo sendConsultingVo,
                                       HttpServletRequest req, Model model) {
        Long userNumber = 0L;
        Long companyNumber = 0L;

        // 다음 코드를 추가합니다.
        ConsultingRequestDto consultingRequestDto = new ConsultingRequestDto();
        consultingRequestDto.setConsultingRequestNumber(sendConsultingVo.getConsultingRequestNumber());
        if ("0".equals(consultingRequestDto.getConsultingCondition())) {
            consultingRequestDto.setConsultingCondition("1"); // 변경된 속성 이름 사용
            consultingService.modifyCondition(consultingRequestDto);
        }


        if (req.getSession().getAttribute("userNumber") != null) {
            userNumber = (Long) req.getSession().getAttribute("userNumber");
            sendConsultingVo.setUserNumber(userNumber);
        } else if (req.getSession().getAttribute("companyNumber") != null) {
            companyNumber = (Long) req.getSession().getAttribute("companyNumber");
            sendConsultingVo.setCompanyNumber(companyNumber);
        }

        System.out.println(sendConsultingVo + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        consultingService.register(sendConsultingVo);

        String path = "/company/SendConsulting?consultingRequestNumber=" + sendConsultingVo.getConsultingRequestNumber() + "&consultingNumber=" + sendConsultingVo.getConsultingNumber();
        System.out.println(path);
        return new RedirectView(path);
    }

    @GetMapping("ConsultingList2")
    public String consultingList2(Criteria criteria,ConsultingRequestDto consultingRequestDto, Model model, HttpServletRequest req) {

        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");


        List<ConsultingRequestDto> consultingRequests =
                consultingService.selectCondition(companyNumber);

        System.out.println(consultingRequests + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        model.addAttribute("pageInfo", new PageVo(criteria, consultingService.getTotal()));
        model.addAttribute("consultingRequests", consultingRequests);
        return "/company/ConsultingList2";

    }

    @GetMapping("/ConsultingList")
    public String consultingList(Criteria criteria,ConsultingRequestDto consultingRequestDto, Model model, HttpServletRequest req) {

        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");


        List<ConsultingRequestDto> consultingRequests =
                consultingService.findAllConsultingRequests(companyNumber);

        System.out.println(consultingRequests + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(new PageVo(criteria, consultingService.getTotal()));
        model.addAttribute("consultingRequests", consultingRequests);
        return "/company/ConsultingList";
    }


    @GetMapping("/delete")
    public RedirectView delete(@RequestParam("consultingRequestNumber")Long consultingRequestNumber ,@RequestParam("consultingNumber") Long consultingNumber, Model model) {

        consultingService.remove(consultingNumber);
        System.out.println("##############################"+consultingNumber);


        String path = "/company/SendConsulting?consultingRequestNumber=" + consultingRequestNumber;
        System.out.println(path);
        return new RedirectView(path);
    }


//    댓글 수정 컨트롤러 ~!
    @PostMapping("/modify")
    public RedirectView modify(ConsultingDto consultingDto) {
// 애를 수정하는거자나 맞지??? 네 맞습니다 죗오함다 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅇ아니야 ㄴㅐ가 틀릴 수도 있으니까 잘 봐서 고쳐줘 틀리면
        // 댓글 수정은 consultingDto 테이블이자나 그치? 그럼 얘에다가 담는게 더 편하지 않겠어?
// 프론트에서 히든으로 컨설팅넘버랑, 컨설팅콘텐츠 두개만 받았잖아 그거 두개로 댓글 수정하는거임 네
        System.out.println("댓글 수정 들어온 값" + consultingDto);
                consultingService.modify(consultingDto);


        String path = "/company/SendConsulting?consultingRequestNumber=" + consultingDto.getConsultingRequestNumber();
        System.out.println(path);
        return new RedirectView(path);
//        경로설정이 안되어서 그런거아닐까요 !? 맞아 이거 할줄 알겠어???그 가져와야하는게
//        consultingNumber, requestNumber,..?인 강뇨ㅗ노노노ㅗ
//        댓글을 바꾸는데 필요한게 consultingNumber고
//        경로설정은 다시 저 페이지로 가야하잖아 그럼 저 페이지는 consultingRequestNumber만 필요하지 그치?네
//        할 수 있어? 해보겠스비다 ..ㅋㅋㅋㅋㅋㅋㅋ별거 없음 진짜
//        저기에 있잖아 ? 댓글 하나하나 다 질문에 대한 답이니까 consultingRequestNubmer의 값이 들어있단 말이지
        }

    @GetMapping("/SendReadList")
    public String sendReadList(Criteria criteria, Model model, HttpServletRequest req) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        System.out.println(userNumber+"-----------------------------");
        List<ConsultingRequestDto> consultingRequestList = consultingService.findAllUser(userNumber, criteria); // 새로운 메서드 사용


        System.out.println(pageVo.getEndPage() );
        System.out.println(pageVo.getStartPage());
        System.out.println(consultingRequestList);
        System.out.println(new PageVo(criteria, consultingService.getTotal()));

        model.addAttribute("pageInfo", new PageVo(criteria, consultingService.getTotal()));
        model.addAttribute("consultingList", consultingRequestList);
        return "company/SendReadList";
    }


//    @PostMapping("/SendReadList")
//    public RedirectView SendReadList(ConsultingReplyVo consultingReplyVo) {
//
//        consultingReplyService.register(consultingReplyVo);
//        System.out.println(consultingReplyVo + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        return new RedirectView("/company/AfterConsulting");
//    }


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
    


    @GetMapping("/WhyRefund")
    public void WhyRefund() {

    }



    @GetMapping("/SendReadList2")
    public void SendReadList2() {

    }


//    @GetMapping("/AfterConsulting")
//    public String AfterConsulting(Model model,Criteria criteria) {
//        // 조회하려는 consultingNumber 설정 (예시: 1L)
//        Long consultingNumber = 1L;
//        SendConsultingVo consulting = consultingMapper.select(consultingNumber);
//
//        // 조회하려는 userNumber 설정 (예시: 1L)
//        Long userNumber = 1L;
//
//        // 전체 컨설팅 목록을 조회합니다.
//        List<SendConsultingVo> consultingList = consultingService.findAll(consultingNumber, criteria);
//
//        model.addAttribute("consulting", consulting);
//        model.addAttribute("consultingNumber", consultingNumber);
//        model.addAttribute("userNumber", userNumber);
//        model.addAttribute("consultingList", consultingList);
//
//                  Long replyNumber=1L;
//       ConsultingReplyVo replyNo = consultingReplyMapper.select(replyNumber);
//
//     List<ConsultingReplyVo> consultingReplyList=consultingReplyMapper.selectAll();
//
//      model.addAttribute("replyNo", replyNo);
//       model.addAttribute("consultingReplyList" , consultingReplyList);
//
//
//
//        return "company/AfterConsulting";
//    }


    @GetMapping("/consultingReview")
    public String consultingReview(HttpServletRequest req,@RequestParam("consultingRequestNumber") Long consultingRequestNumber ,Model model) {
//        Long userNumber = (long)req.getSession().getAttribute("userNumber");
        System.out.println("@@consultingRequestNumber@@@");
        System.out.println(consultingRequestNumber);
//        Long consultingRequestNumber = (long)req.getSession().getAttribute("consultingRequestNumber");
//        Long consultingRequestNumber = 7L;
        ConsultingReviewVo consultingReviewVo = reviewService.findConsultingInfo(consultingRequestNumber);
        System.out.println("%%%%%%");
        System.out.println(consultingReviewVo);

        model.addAttribute("consultingReviewVo", consultingReviewVo);
        return "company/consultingReview";
    }

    @PostMapping("/consultingReview")
    public RedirectView consultingReviewWrite(ConsultingReviewVo consultingReviewVo){
        reviewService.register(consultingReviewVo);
        reviewService.updateConsultingCondition(consultingReviewVo.getConsultingRequestNumber());
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
    public String consultingPayInfo(HttpServletRequest req, Model model, Criteria criteria){
        //      Long userNumber = (long)req.getSession().getAttribute("userNumber");
        Long userNumber = 1L;
        List<ConsultingPayVo> consultingPayVo = reviewService.findConsultingOrderList(userNumber, criteria);
        System.out.println("%%%컨설팅 구매내역 리스트%%%");
        System.out.println(consultingPayVo);

        model.addAttribute("consultingPayVo", consultingPayVo);
        model.addAttribute("pageInfo", new PageVo(criteria, reviewService.orderConsultingListCount()));
        System.out.println(new PageVo(criteria, reviewService.orderConsultingListCount()));
        return "company/consultingPayInfo";
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


