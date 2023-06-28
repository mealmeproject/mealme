package com.example.mealme.mapper;

import com.example.mealme.dto.CompanyDto;
import com.example.mealme.dto.UserDto;
import com.example.mealme.dto.UserFileDto;
import com.example.mealme.vo.CompanyModifyVo;
import com.example.mealme.vo.UserModifyVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 유저회원가입
    public void insertUser(UserDto userDto);

    // 유저 아이디 중복검사
    public int checkUserId(String userId);

    // 유저로그인
    public Long selectUserNumber(String userId, String userPassword);

    // 기업회원가입
    public void insertCompany(CompanyDto companyDto);

    // 기업 아이디 중복검사
    public int checkCompanyId(String companyId);

    // 기업로그인
    public Long selectCompanyNumber(String companyId, String companyPassword);

    // 유저 회원정보수정 체크
    public String checkUser(long userNumber, String userPassword);

    // 기업 회원정보수정 체크
    public String checkCompany(long companyNumber, String companyPassword);

    // 유저 회원정보수정 조회
    public UserModifyVo checkUser2(long userNumber);

    // 기업 회원정보수정 조회
    public CompanyModifyVo checkCompany2(long companyNumber);

    // 유저 닉네임 업데이터
    public void updateUserNickname(long userNumber, String userNickname);

    // 유저 코멘트 업데이트
    public void updateUserComment(long userNumber, String userComment);

    // 유저 업데이트
    public void updateUser(UserDto userDto);

    // 유저 프로필 사진 체크
    public boolean checkUserProfileImg(long userNumber);

    // 유저 프로필 삭제
    public void deleteUserProfileImg(long userNumber);

    // 유저 프로필 삽입
    public void insertUserProfileImg(UserFileDto userFileDto);


    // 기업

}
