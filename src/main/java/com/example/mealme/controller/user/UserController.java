package com.example.mealme.controller.user;

import com.example.mealme.dto.CompanyDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.service.user.UserFileService;
import com.example.mealme.service.user.UserService;
import com.example.mealme.util.Util;
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

    // 밀미 홈페이지 경로처리
    @GetMapping("/index")
    public void index() {
    }

    // 로그인 페이지 경로 처리
    @GetMapping("/login")
    public void login() {
    }

    // 로그인 페이지 입력받은 값에 따라 개인 / 기업 확인 후 마이페이지 / 받은 컨설팅 리스트 페이지로 경로처리
    @PostMapping("/login")
    public RedirectView login(String userType, String id, String password, HttpServletRequest req) {
        // 유저가 개인회원인지 기업회원인지 체크
        if (userType.equals("user")) {                          // 개인회원
            try {
                Long userNumber = userService.findUserNumber(id, password);
                req.getSession().setAttribute("userNumber", userNumber);
            } catch (Exception e) {
                e.printStackTrace();
                return new RedirectView("user/login");
            }
            return new RedirectView("/meal/myPage");
        } else if (userType.equals("company")) {                // 기업회원
            try {
                Long companyNumber = userService.findCompanyNumber(id, password);
                req.getSession().setAttribute("companyNumber", companyNumber);
            } catch (Exception e) {
                e.printStackTrace();
                return new RedirectView("user/login");
            }
            return new RedirectView("/company/ConsultingList");
        } else {
            return new RedirectView("user/login");
        }
    }

    // 회원 가입 시 개인 / 기업 선택 페이지
    @GetMapping("/userOrCompany")
    public void userOrCompany() {
    }

    // 개인 회원가입 경로 처리
    @GetMapping("/userJoin")
    public void userJoin() {
    }

    // 개인 회원가입 정보 저장 후 가입 완료 페이지로 경로 처리
    @PostMapping("/userJoin")
    public RedirectView join(UserVo userVo) {
        // 받은 Vo를 userDto로 바꿔주는 메소드 실행 (ex) 전화번호 합치기 (010/1234/1234))
        UserDto userDto = userVo.userVoToUserDto();
        userService.registerUser(userDto);
        Long userNumber = userDto.getUserNumber();
        String path = "/user/userJoinOk?userNumber=" + userNumber;
        return new RedirectView(path);
    }

    // 개인 회원가입 페이지 아이디 체크
    @PostMapping("/checkUserId")
    @ResponseBody
    public Map<Object, Object> checkUserId(String userId) {
        System.out.println("받아온 유저 아이디 " + userId);
        boolean checkUserId = userService.checkUserId(userId);
        Map<Object, Object> map = new HashMap<Object, Object>();

        map.put("checkId", checkUserId);

        return map;
    }

    // 회원 가입 완료 페이지
    @GetMapping("/userJoinOk")
    public void userJoinOk(@RequestParam("userNumber") Long userNumber, Model model) {
        String userName = userService.getUserName(userNumber);
        model.addAttribute("userName", userName);
    }

    // 카카오 로그인
    @PostMapping("/KakaoUserLogin")
    public RedirectView KakaoUserLogin(UserDto userDto) {
    userDto.setUserGender(Util.convertGender(userDto.getUserGender()));
    userDto.setUserBirth(Util.convertBirth(userDto.getUserBirth()));
        System.out.println(userDto);
    userService.registerKakaoUser(userDto);
    return new RedirectView("/user/userJoinOk");
    }

    // 기업 회원가입 경로처리
    @GetMapping("/companyJoin")
    public void companyJoin() {
    }

    // 기업 회원가입 정보 저장 처리
    @PostMapping("/companyJoin")
    public RedirectView companyJoin(CompanyVo companyVo, HttpServletRequest req, RedirectAttributes redirectAttributes
            , @RequestParam("companyRegistrationFile") MultipartFile file) {
        // 입력받은 companyVo 를 companyDto로 변환 시켜서 저장
        CompanyDto companyDto = companyVo.companyVoToCompanyDto();
        userService.registerCompany(companyDto);
        // 저장하면서 나온 companyNumber 변수에 저장
        Long companyNumber = companyDto.getCompanyNumber();
        // 사업자등록증 사진파일 저장처리
        redirectAttributes.addFlashAttribute("companyNumber", companyNumber);
        if (file != null) {
            try {
                userFileService.companyRegistrationRegisterAndSaveFiles(file, companyNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        String path = "/user/companyJoinOk?companyNumber=" + companyNumber;
        return new RedirectView(path);
    }

    // 기업 회원가입시 아이디 중복검사
    @GetMapping("/checkCompanyId")
    @ResponseBody
    public Map<Object, Object> checkCompanyId(@RequestParam("companyId") String companyId) {
        boolean checkCompanyId = userService.checkCompanyId(companyId);
        Map<Object, Object> map = new HashMap<Object, Object>();

        map.put("checkId", checkCompanyId);

        return map;
    }

    // 기업 회원가입 완료 페이지
    @GetMapping("/companyJoinOk")
    public void companyJoinOk(@RequestParam("companyNumber") Long companyNumber, Model model) {
        String companyName = userService.getCompanyName(companyNumber);
        model.addAttribute("companyName", companyName);
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate(); // 세션 초기화
        return "user/login";
    }

    // 비밀번호 찾기 경로 처리
    @GetMapping("/findPassword")
    public void findPassword() {
    }

    @PostMapping("/findPassword")
    public void findPassword(UserDto userDto){

        if(userService.findPassword(userDto)){

        };
    }

    // 비밀번호 찾기 완료 페이지 (메일로 전송 했다는 메세지 출력)
    @GetMapping("/findPasswordOk")
    public void findPasswordOk() {
    }

    // 정보 수정 경로처리 (세션의 정보에 따라서 개인 / 기업 회원 정보수정 페이지로 보냄)
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

    // 개인회원 정보 수정 페이지 1
    @GetMapping("/userUpdate")
    public void userUpdate(HttpServletRequest req, Model model) {
        long userNumber = (long) req.getSession().getAttribute("userNumber");

        String userName = userService.getUserName(userNumber);

        model.addAttribute("userName", userName);
    }

    // 개인회원 정보 수정페이지 1 / 비밀번호 확인 후 수정페이지로 보냄
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

    // 개인회원 정보 수정 페이지 2 / 회원의 데이터 불러와서 보여줌
    @GetMapping("/userUpdate2")
    public String userUpdate2(HttpServletRequest req, Model model) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        String userId = req.getParameter("userId");
        UserModifyVo userModifyVo = userService.findUser(userNumber).userModifyVoSetUp();
        System.out.println(userModifyVo);
        model.addAttribute("user", userModifyVo);
        return "user/userUpdate2";
    }

    // 개인회원 정보 수정 페이지 2 / 유저 프로필 업데이트
    @PostMapping("/userProfileUpdate")
    public RedirectView userProfileUpdate(HttpServletRequest req, RedirectAttributes redirectAttributes
            , @RequestParam("userProfileFile") MultipartFile file){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        redirectAttributes.addFlashAttribute("userNumber", userNumber);
        // 해야하는 것 userProfile file 이 있을때 삭제 처리하고 넣어야 한다.
        System.out.println("있는 파일 체크==============================");
        int check = userFileService.userProfileFileCheck(userNumber);
        System.out.println(check);
        if (check > 0){
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

    // 개인회원 정보 수정 페이지 2 / 유저 닉네임 업데이트
    @PostMapping("/userNicknameUpdate")
    public RedirectView userNicknameUpdate(HttpServletRequest req, String userNickname){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        userService.modifyNickname(userNumber, userNickname);
        System.out.println("닉네임 수정완료 !");
        return new RedirectView("/user/userUpdate2");
    }

    // 개인회원 정보 수정 페이지 2 / 유저 코멘트 업데이트
    @PostMapping("/userCommentUpdate")
    public RedirectView userCommentUpdate(HttpServletRequest req, String userComment){
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        userService.modifyComment(userNumber, userComment);
        System.out.println("코멘트 수정완료 !");
        return new RedirectView("/user/userUpdate2");
    }

    // 개인회원 정보 수정 페이지 2 / 유저 기타정보 수정
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

    // 기업 정보 수정 페이지 1 / 경로 처리
    @GetMapping("/companyUpdate")
    public void companyUpdate(HttpServletRequest req, Model model) {
        long companyNumber = (long) req.getSession().getAttribute("companyNumber");

        String companyName = userService.getCompanyName(companyNumber);

        model.addAttribute("companyName", companyName);
    }

    // 기업 정보 수정 페이지 1 / 입력받은 비밀번호 확인
    @PostMapping("/companyUpdate")
    public RedirectView companyUpdate(String companyPassword, HttpServletRequest req) {
        System.out.println("기업회원수정 비밀번호 확인중 !");
        Long companyNumber = (long) req.getSession().getAttribute("companyNumber");
        String companyId = userService.companyCheck(companyNumber, companyPassword);
        if (companyId != null) {
            String redirectUrl = "/user/companyUpdate2?companyId=" + companyId;
            return new RedirectView(redirectUrl);
        } else {
            return new RedirectView("user/login");
        }
    }

    // 기업 정보 수정 페이지 2 / 기업 정보 뿌려주기
    @GetMapping("/companyUpdate2")
    public String companyUpdate2(HttpServletRequest req, Model model) {
        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");
        String companyId = req.getParameter("companyId");
        CompanyModifyVo companyModifyVo = userService.findCompany(companyNumber).companyModifyVoSetUp();
        System.out.println(companyModifyVo);
        model.addAttribute("company", companyModifyVo);
        return "user/companyUpdate2";
    }

    // 개인회원 정보 수정 페이지 2 / 기업 프로필 업데이트
    @PostMapping("/companyProfileUpdate")
    public RedirectView companyProfileUpdate(HttpServletRequest req, RedirectAttributes redirectAttributes
            , @RequestParam("companyProfileFile") MultipartFile file){
        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");
        redirectAttributes.addFlashAttribute("companyNumber", companyNumber);
        // 해야하는 것 userProfile file 이 있을때 삭제 처리하고 넣어야 한다.
        if (userFileService.companyProfileFileCheck(companyNumber)){
            userFileService.companyRemove(companyNumber);
        }
        if (file != null) {
            try {
                userFileService.companyRegisterAndSaveFiles(file, companyNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new RedirectView("/user/companyUpdate2");
    }

    // 기업회원 정보 수정 페이지 2 / 기업 닉네임 업데이트
    @PostMapping("/companyNameUpdate")
    public RedirectView companyNameUpdate(HttpServletRequest req, String companyName){
        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");
        System.out.println(companyName+ "companyName~~~~~~~~~~~~~~");
        System.out.println(companyNumber);
        userService.modifyCompanyName(companyNumber, companyName);
        System.out.println("이름 수정완료 !");
        return new RedirectView("/user/companyUpdate2");
    }

    // 기업회원 정보 수정 페이지 2 / 기업 코멘트 업데이트
    @PostMapping("/companyCommentUpdate")
    public RedirectView companyCommentUpdate(HttpServletRequest req, String companyComment){
        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");
        userService.modifyCompanyComment(companyNumber, companyComment);
        System.out.println("코멘트 수정완료 !");
        return new RedirectView("/user/companyUpdate2");
    }

    // 기업회원 정보 수정 페이지 2 / 기업 기타정보 수정
    @PostMapping("/companyUpdate2")
    public RedirectView companyModify(CompanyModifyVo companyModifyVo, HttpServletRequest req){
        System.out.println("컴퍼니회원 수정 컨트롤러 !");
        Long companyNumber = (Long) req.getSession().getAttribute("companyNumber");
        System.out.println(companyModifyVo);
        CompanyDto companyDto = companyModifyVo.companyVoToDto();
        companyDto.setCompanyNumber(companyNumber);
        userService.modifyCompany(companyDto);
        System.out.println("기업 회원 수정 완료 !");
        return new RedirectView("/user/companyUpdate2");
    }

}

