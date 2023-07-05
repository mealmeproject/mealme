package com.example.mealme.mapper;

import com.example.mealme.dto.ConsultingDto;
import com.example.mealme.dto.ConsultingReplyDto;
import com.example.mealme.dto.ConsultingRequestDto;
import com.example.mealme.vo.ConsultingVo;
import com.example.mealme.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsultingMapper {

    void insert(ConsultingVo consultingVo);

    public List<ConsultingVo> selectAll();

    ConsultingVo select(Long consultingNumber);

    void delete(Long consultingNumber);

    ConsultingVo selectComment(long consultingNumber);

    //전체조회
    public List<ConsultingVo> selectAll(Criteria criteria);

    //    //전체 게시글 수 조회
    public int selectTotal();


    //request쪽임
    List<ConsultingRequestDto> selectRequestAll();

    public void selectRequestOne(long requestNumber);



}