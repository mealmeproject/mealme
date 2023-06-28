package com.example.mealme.controller.user;

import com.example.mealme.dto.CompanyDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.service.user.UserFileService;
import com.example.mealme.service.user.UserService;
import com.example.mealme.vo.CompanyModifyVo;
import com.example.mealme.vo.CompanyVo;
import com.example.mealme.vo.UserModifyVo;
import com.example.mealme.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserFileService userFileService;

    @GetMapping("/index")
    public void index() {
    }

    @GetMapping("/login")
    public void login() {
    }

    @PostMapping("/login")
    public RedirectView login(String userType, String id, String password, HttpServletRequest req) {
        // 유저가 개인회원인지 기업회원인지 체크
        if (userType.equals("user")) {
            try {
                Long userNumber = userService.findUserNumber(id, password);
                req.getSession().setAttribute("userNumber", userNumber);
            } catch (Exception e) {
                e.printStackTrace();
                return new RedirectView("user/login");
            }
            return new RedirectView("/meal/myPage");
        } else if (userType.equals("company")) {
            try {
                Long companyNumber = userService.findCompanyNumber(id, password);
                req.getSession().setAttribute("companyNumber", companyNumber);
            } catch (Exception e) {
                e.printStackTrace();
                return new RedirectView("user/login");
            }
//            컴퍼니회원 로그인 페이지
            return new RedirectView("/company/ConsultingList");
        } else {
            return new RedirectView("user/login");
        }
    }

    @GetMapping("/userOrCompany")
    public void userOrCompany() {
    }

    @GetMapping("/userJoin")
    public void userJoin() {
    }

    @PostMapping("/userJoin")
    public RedirectView join(UserVo userVo) {
        UserDto userDto = userVo.userVoToUserDto();
        userService.registerUser(userDto);
        return new RedirectView("/user/userJoinOk");
    }

    @GetMapping("/checkUserId")
    @ResponseBody
    public Map<Object, Object> checkUserId(@RequestParam("userId") String userId) {
        boolean checkUserId = userService.checkUserId(userId);
        Map<Object, Object> map = new HashMap<Object, Object>();

        map.put("checkId", checkUserId);

        return map;
    }

    @GetMapping("/userJoinOk")
    public void userJoinOk() {
    }

    @GetMapping("/userKakaoLogin")
    public void userKakaoLogin() {
    }

    @GetMapping("/companyJoin")
    public void companyJoin() {
    }

    @PostMapping("/companyJoin")
    public RedirectView companyJoin(CompanyVo companyVo, HttpServletRequest req, RedirectAttributes redirectAttributes
            , @RequestParam("companyRegistrationFile") MultipartFile file) {
        CompanyDto companyDto = companyVo.companyVoToCompanyDto();
        userService.registerCompany(companyDto);
        // 사업자등록증 사진파일 저장처리
        Long companyNumber = companyDto.getCompanyNumber();

        redirectAttributes.addFlashAttribute("companyNumber", companyNumber);
        if (file != null) {
            try {
                userFileService.companyRegistrationRegisterAndSaveFiles(file, companyNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/user/companyJoinOk");
    }

    @GetMapping("/checkCompanyId")
    @ResponseBody
    public Map<Object, Object> checkCompanyId(@RequestParam("companyId") String companyId) {
        boolean checkCompanyId = userService.checkCompanyId(companyId);
        Map<Object, Object> map = new HashMap<Object, Object>();

        map.put("checkId", checkCompanyId);

        return map;
    }

    @GetMapping("/companyJoinOk")
    public void companyJoinOk() {
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate(); // 세션 초기화
        return "user/login";
    }

    @GetMapping("/findPassword")
    public void findPassword() {
    }

    @GetMapping("/findPasswordOk")
    public void findPasswordOk() {
    }

    @GetMapping("/user/userOrCompanyUpdate")
    public RedirectView userOrCompanyUpdate(HttpServletRequest req) {
        if (req.getSession().getAttribute("userNumber") != null) {
            System.out.println("유저회원수정!");
            return new RedirectView("/user/userUpdate");
        } else if (req.getSession().getAttribute("companyNumber") != null) {
            System.out.println("기업회원수정!");
            return new RedirectView("/user/companyUpdate");
        } else {
            return new RedirectView("/user/login");
        }
    }

    @GetMapping("/userUpdate")
    public void userUpdate() {
    }

    @PostMapping("/userUpdate")
    public RedirectView userUpdate(String userPassword, HttpServletRequest req) {
        long userNumber = (long) req.getSession().getAttribute("userNumber");
        String userId = userService.userCheck(userNumber, userPassword);
        if (userId != null) {
            String redirectUrl = "/user/userUpdate2?userId=" + userId;
            return new RedirectView(redirectUrl);
        } else {
            return new RedirectView("/user/login");
        }
    }

    @GetMapping("/userUpdate2")
    public String userUpdate2(HttpServletRequest req, Model model) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        String userId = req.getParameter("userId");
        UserModifyVo userModifyVo = userService.findUser(userNumber).userModifyVoSetUp();
        System.out.println(userModifyVo);
        model.addAttribute("user", userModifyVo);
        return "user/userUpdate2";
    }

    // 유저 프로필 업데이트
    @PostMapping("/userProfileUpdate")
    public RedirectView userProfileUpdate(HttpServletRequest req, RedirectAttributes redirectAttributes
            , @RequestParam("userProfileFile") MultipartFile file){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        redirectAttributes.addFlashAttribute("userNumber", userNumber);
        // 해야하는 것 userProfile file 이 있을때 삭제 처리하고 넣어야 한다.
        if (userFileService.userProfileFileCheck(userNumber)){
            userFileService.userRemove(userNumber);
        }
        if (file != null) {
            try {
                userFileService.userRegisterAndSaveFiles(file, userNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/user/userUpdate2");
    }

    // 유저 닉네임 업데이트
    @PostMapping("/userNicknameUpdate")
    public RedirectView userNicknameUpdate(HttpServletRequest req, String userNickname){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        userService.modifyNickname(userNumber, userNickname);
        System.out.println("닉네임 수정완료 !");
        return new RedirectView("/user/userUpdate2");
    }

    // 유저 코멘트 업데이트
    @PostMapping("/userCommentUpdate")
    public RedirectView userCommentUpdate(HttpServletRequest req, String userComment){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        userService.modifyComment(userNumber, userComment);
        System.out.println("코멘트 수정완료 !");
        return new RedirectView("/user/userUpdate2");
    }

    // 유저 기타정보 수정
    @PostMapping("/userUpdate2")
    public RedirectView userModify(UserModifyVo userModifyVo, HttpServletRequest req){
        System.out.println("유저회원 수정 컨트롤러 !");
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        System.out.println(userModifyVo);
        UserDto userDto = userModifyVo.userModifyVoToUserDto();
        System.out.println(userDto);
        userDto.setUserNumber(userNumber);
        userService.modifyUser(userDto);
        System.out.println("유저 회원 수정 완료 !");
        return new RedirectView("/user/userUpdate2");

    }

    @GetMapping("/companyUpdate")
    public void companyUpdate() {
    }

    @PostMapping("/companyUpdate")
    public RedirectView companyUpdate(String companyPassword, HttpServletRequest req) {
        Long companyNumber = (long) req.getSession().getAttribute("companyNumber");
        String companyId = (userService.companyCheck(companyNumber, companyPassword));
        if (companyId != null || companyNumber != null) {
            String redirectUrl = "/user/companyUpdate2?companyId=" + companyId;
            return new RedirectView(redirectUrl);
        } else {
            return new RedirectView("user/login");
        }
    }

    @GetMapping("/companyUpdate2")
    public String companyUpdate2(HttpServletRequest req, Model model) {
        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");
        String companyId = req.getParameter("companyId");
        CompanyModifyVo companyModifyVo = userService.findCompany(companyNumber);
        model.addAttribute(companyModifyVo);
        return "user/companyUpdate2";
    }
}

