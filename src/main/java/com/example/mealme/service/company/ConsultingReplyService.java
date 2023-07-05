package com.example.mealme.service.company;


import com.example.mealme.dto.ConsultingReplyDto;
import com.example.mealme.mapper.ConsultingReplyMapper;
import com.example.mealme.vo.ConsultingReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class ConsultingReplyService {
    private final ConsultingReplyMapper consultingReplyMapper;

    public void register(ConsultingReplyVo consultingReplyVo) {
        if (consultingReplyVo == null) {
            throw new IllegalArgumentException("게시물 정보가 없습니다");
        }
        consultingReplyMapper.insert(consultingReplyVo);
    }


    public void modify(ConsultingReplyVo consultingReplyVo) {
        if (consultingReplyVo == null) {
            throw new IllegalArgumentException("댓글 수정정보 누락");
        }
        consultingReplyMapper.update(consultingReplyVo);
    }

    public void remove(Long replyNumber) {
        if (replyNumber == null) {
            throw new IllegalArgumentException("댓글 번호 누락");
        }
        consultingReplyMapper.delete(replyNumber);


    }
}
