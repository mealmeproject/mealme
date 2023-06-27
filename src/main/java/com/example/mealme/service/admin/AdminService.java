package com.example.mealme.service.admin;

import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.AdminMapper;
import com.example.mealme.vo.SearchVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminMapper adminMapper;

    public void register(UserDto userDto){
        if(userDto == null){
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }
        adminMapper.insert(userDto);
    }


//    전체 조회
    public List<UserDto> findAll(){
        return adminMapper.selectAll();
    }

//    검색 조회
    public List<UserDto> searchUserList(SearchVo searchVo){
        return adminMapper.searchUser(searchVo);
    }

//    유저 삭제
    public void deleteUserList(List<String> checkBoxArr){
        if(checkBoxArr == null){
            throw new IllegalArgumentException("회원을 선택하세요.");
        }

        for(int i=0; i<checkBoxArr.size(); i++){
            adminMapper.delete(checkBoxArr.get(i));
        }


    }
}
