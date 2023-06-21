package com.example.mealme.controller.user;

import com.example.mealme.dto.UserDto;
import com.example.mealme.service.user.UserService;
import com.example.mealme.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/index")
    public void index(){}

    @GetMapping("/login")
    public void login(){}

    @PostMapping("/login")
    public RedirectView login(String userType, String id, String password, HttpServletRequest req){
        // 유저가 개인회원인지 기업회원인지 체크
        if (userType.equals("user")){
            try {
                Long userNumber = userService.findUserNumber(id, password);
                req.getSession().setAttribute("userNumber", userNumber);
            } catch (Exception e) {
                e.printStackTrace();
                return new RedirectView("user/login");
            }
            return new RedirectView("/meal/myPage");
        }else if (userType.equals("company")){
//            try {
//                Long companyNumber = 0L;
//                req.getSession().setAttribute("companyNumber", companyNumber);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return new RedirectView("user/login");
//            }
////            컴퍼니회원 로그인 페이지
            return new RedirectView("/company/ConsultingList");
        }
        return new RedirectView("user/login");
    }

    @GetMapping("/userOrCompany")
    public void userOrCompany(){}

    @GetMapping("/userJoin")
    public void userJoin(){}

    @PostMapping("/userJoin")
    public RedirectView join(UserVo userVo){
        UserDto userDto = userVo.userVoToUserDto();
        userService.register(userDto);
        return new RedirectView("/user/userJoinOk");
    }

    @GetMapping("/userJoinOk")
    public void userJoinOk(){}

    @GetMapping("/companyJoin")
    public void companyJoin(){}

    @GetMapping("/companyJoinOk")
    public void companyJoinOk(){}

    @GetMapping("/findPassword")
    public void findPassword(){}

    @GetMapping("/findPasswordOk")
    public void findPasswordOk(){}

    @GetMapping("/userUpdate")
    public void userUpdate(){}

    @GetMapping("/userUpdate2")
    public void userUpdate2(){}

    @GetMapping("/companyUpdate")
    public void companyUpdate(){}

    @GetMapping("/companyUpdate2")
    public void companyUpdate2(){}

}

