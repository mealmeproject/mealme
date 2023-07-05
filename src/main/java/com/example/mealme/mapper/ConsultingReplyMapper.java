package com.example.mealme.mapper;

import com.example.mealme.dto.ConsultingReplyDto;
import com.example.mealme.vo.ConsultingReplyVo;
import com.example.mealme.vo.ConsultingVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ConsultingReplyMapper {

    //삽입

    void insert(ConsultingReplyVo consultingReplyVo);

    //선택
    ConsultingReplyVo select(Long replyNumber);

        //전체선택
    public List<ConsultingReplyVo> selectAll();

    //업데이트
    public void update(ConsultingReplyVo consultingReplyVo);

    //삭제

    public void delete (Long replyNumber);


}
