package com.example.mealme.mapper;

import com.example.mealme.dto.ConsultingDto;
import com.example.mealme.dto.ConsultingRequestDto;
import com.example.mealme.vo.ConsultingRequestVo;
import com.example.mealme.vo.SendConsultingVo;
import com.example.mealme.vo.Criteria;
import com.example.mealme.vo.MealVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsultingMapper {

    void insert(SendConsultingVo sendConsultingVo);

    SendConsultingVo select(Long consultingNumber);

//    삭제
    void delete(Long consultingNumber);
    // 컨설팅 코멘트 업데이트
    void update(ConsultingDto consultingDto);

    SendConsultingVo selectComment(long consultingNumber);

    //전체조회
    public List<SendConsultingVo> selectAll(@Param("consultingRequestNumber") Long consultingRequestNumber,
                                            @Param("criteria") Criteria criteria);

    //    //전체 게시글 수 조회
    public int selectTotal();


    //request쪽임
    List<ConsultingRequestDto> selectRequestAll(@Param("companyNumber") Long companyNumber);


    //
    public ConsultingRequestVo selectRequestOne(long consultingRequestNumber);


    // 컨설팅 신청 읽기 페이지 식단 가져오는 매퍼
    public List<MealVo> getMealList(ConsultingRequestVo consultingRequestVo);


    List<ConsultingRequestDto> selectAllUser(@Param("userNumber") Long userNumber, @Param("criteria") Criteria criteria);


//    Request Condition Update문


    void updateCondition(ConsultingRequestDto consultingRequestDto);

    //condition가져오기

    List<ConsultingRequestDto> findConditionOneUser(@Param("companyNumber") Long companyNumber);




}