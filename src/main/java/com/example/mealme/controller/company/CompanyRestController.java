package com.example.mealme.controller.company;

import com.example.mealme.service.company.CompanyService;
import com.example.mealme.vo.ConsultingVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/companies/*")
@RequiredArgsConstructor
public class CompanyRestController {
    public final CompanyService companyService;

    @PostMapping("/settingThePeriods")
    public String insert(@RequestBody ConsultingVo consultingVo, HttpServletRequest req){
//        System.out.println(consultingVo);
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        consultingVo.setUserNumber(userNumber);
        companyService.register(consultingVo);
        return "settingThePeriod";
    }
}

