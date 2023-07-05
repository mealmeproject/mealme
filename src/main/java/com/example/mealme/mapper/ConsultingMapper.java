package com.example.mealme.mapper;

import com.example.mealme.dto.ConsultingRequestDto;
import com.example.mealme.vo.ConsultingRequestVo;
import com.example.mealme.vo.SendConsultingVo;
import com.example.mealme.vo.Criteria;
import com.example.mealme.vo.MealVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsultingMapper {

    void insert(SendConsultingVo sendConsultingVo);

    public List<SendConsultingVo> selectAll();

    SendConsultingVo select(Long consultingNumber);

    void delete(Long consultingNumber);

    SendConsultingVo selectComment(long consultingNumber);

    //전체조회
    public List<SendConsultingVo> selectAll(Criteria criteria);

    //    //전체 게시글 수 조회
    public int selectTotal();


    //request쪽임
    List<ConsultingRequestDto> selectRequestAll(Long companyNumber);


    //
    public ConsultingRequestVo selectRequestOne(long consultingRequestNumber);


    // 컨설팅 신청 읽기 페이지 식단 가져오는 매퍼
    public List<MealVo> getMealList(ConsultingRequestVo consultingRequestVo);


}