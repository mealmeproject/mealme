package com.example.mealme.controller.admin;

import com.example.mealme.dto.UserDto;
import com.example.mealme.service.admin.AdminService;
import com.example.mealme.vo.Criteria;
import com.example.mealme.vo.PageVo;
import com.example.mealme.vo.SearchVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admins/v1/*")
public class AdminRestController {
    private final AdminService adminService;

    @GetMapping("/user")
    public List<UserDto> showUserList(){
        return adminService.findAll();
    }

//    @GetMapping("/searchUserList")
//    public List<UserDto> searchUserList(String keyword, String searchType){
//        SearchVo searchVo = new SearchVo();
//        searchVo.setKeyword(keyword);
//        searchVo.setSearchType(searchType);
//        return adminService.searchUserList(searchVo);
//    }


    @PostMapping(value = "/deleteUserList")
    public Map<String, List<String>> deleteUserList(@RequestBody Map<String, List<String>> checkBoxArr) {
        System.out.println("=============================================================");
        System.out.println(checkBoxArr);
        List<String> numberList = checkBoxArr.get("checkBoxArr");
//        adminService.deleteUserList(numberList);


        String checkNum = "";
        for(String str : numberList){
            checkNum = str;
            adminService.deleteUserList(Collections.singletonList(checkNum));
        }
        return checkBoxArr;
    }

    @GetMapping("/searchUserList/{page}")
    public Map<String, Object> findUserList(@PathVariable("page") int page, SearchVo searchVo){
        Criteria criteria = new Criteria(page, 10);

        System.out.println("====================================================================================");
        System.out.println(searchVo);
        System.out.println("====================================================================================");

        PageVo pageVo = new PageVo(criteria, adminService.getTotal(searchVo));
        List<UserDto> searchUser = adminService.searchUserList(searchVo, criteria);

//        List<UserDto> userList = adminService.findAll(criteria);
        Map<String, Object> findUser = new HashMap<>();
        findUser.put("pageVo",pageVo);
//        findUser.put("userList", userList);
        findUser.put("userList", searchUser);
        return findUser;
    }
}
