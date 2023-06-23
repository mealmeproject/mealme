package com.example.mealme.controller.admin;

import com.example.mealme.dto.UserDto;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.vo.SearchVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/v1/*")
public class AdminRestController {
    private final AdminService adminService;

    @GetMapping("/user")
    public List<UserDto> showUserList(){
        return adminService.findAll();
    }

    @GetMapping("/searchUserList")
    public List<UserDto> searchUserList(String keyword, String searchType){
        SearchVo searchVo = new SearchVo();
        searchVo.setKeyword(keyword);
        searchVo.setSearchType(searchType);
        return adminService.searchUserList(searchVo);
    }
}
