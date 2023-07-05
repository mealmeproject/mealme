package com.example.mealme.service.company;

import com.example.mealme.dto.ConsultingReplyDto;
import com.example.mealme.dto.ConsultingRequestDto;
import com.example.mealme.mapper.ConsultingMapper;
import com.example.mealme.vo.ConsultingVo;
import com.example.mealme.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultingService {
    private final ConsultingMapper consultingMapper;
    private final ConsultingVo consultingVo;


    public void register(ConsultingVo consultingVo){
        if (consultingVo == null) {
            throw new IllegalArgumentException("게시물 정보가 없습니다");
        }
        consultingMapper.insert(consultingVo);
    }



    public List<ConsultingRequestDto> findAllConsultingRequests() {
        return consultingMapper.selectRequestAll();
    }





    // 전체 조회
    @Transactional(readOnly = true)
    public List<ConsultingVo> selectAll() {
        if (consultingVo == null) {
            throw new IllegalArgumentException("게시물 정보가 없습니다");
        }
        return consultingMapper.selectAll();
    }



    //    삭제
    public void remove(Long consultingNumber){
        if (consultingNumber == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물");

            }
        consultingMapper.delete(consultingNumber);
    }

//    조회

    /**
     *
     * @param consultingNumber
     * @return
     * @throws IllegalArgumentException 게시물 번호가 존재하지 않으면 발생된다.
     */
    public ConsultingVo findConsulting(Long consultingNumber){
        if(consultingNumber == null){
            throw new IllegalArgumentException("게시물 번호가 없습니다.");
        }
        return Optional.ofNullable(consultingMapper.select(consultingNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 게시물 번호");});
    }

    //    전체 조회
    @Transactional(readOnly = true)
    public List<ConsultingVo> findAll(Criteria criteria){
        return consultingMapper.selectAll(criteria);
    }

    //전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal(){
        return consultingMapper.selectTotal();
    }

}








