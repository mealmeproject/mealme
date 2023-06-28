package com.example.mealme.mapper;

import com.example.mealme.dto.CompanyRegistrationFileDto;
import com.example.mealme.dto.UserFileDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFileMapper {

    // 기업 사업자등록증파일 등록
    public void registrationInsert(CompanyRegistrationFileDto companyRegistrationFileDto);

    // 기업 사업자등록증 검색
    public CompanyRegistrationFileDto registrationSelect(Long companyNumber);

    // 기업 사업자등록증 삭제
    public void registrationDelete(Long companyNumber);

    // 유저 프로필 사진 체크
    public int userCheck(long userNumber);

    // 유저 프로필 사진 입력
    public void userInsert(UserFileDto userFileDto);

    // 유저 프로필 사진 검색
    public UserFileDto userSelect(Long userNumber);

    // 유저 프로필 사진 삭제
    public void userDelete(Long userNumber);



}
