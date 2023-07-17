package com.example.mealme.service.company;

import com.example.mealme.dto.BoardFileDto;
import com.example.mealme.dto.ConsultingDto;
import com.example.mealme.dto.ConsultingRequestDto;
import com.example.mealme.dto.FoodDto;
import com.example.mealme.mapper.ConsultingMapper;
import com.example.mealme.mapper.MealMapper;
import com.example.mealme.util.MealUtil;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultingService {
    private final ConsultingMapper consultingMapper;
    private final MealMapper mealMapper;
    private final SendConsultingVo sendConsultingVo;


    public void register(SendConsultingVo sendConsultingVo) {
        if (sendConsultingVo == null) {
            throw new IllegalArgumentException("게시물 정보가 없습니다");
        }
        consultingMapper.insert(sendConsultingVo);
    }


    public List<ConsultingRequestDto> findAllConsultingRequests(Long companyNumber) {
        if (companyNumber == null) {
            throw new IllegalArgumentException("회사 넘버 없음 !! ");
        }

        return consultingMapper.selectRequestAll(companyNumber);
    }


    public List<ConsultingRequestDto> selectCondition(Long companyNumber){
        if (companyNumber == null){
            throw new IllegalArgumentException("컨설팅 신청 게시글 번호 없음 ! ");
        }
        return consultingMapper.findConditionOneUser(companyNumber);
    }

    public List<ConsultingRequestDto> findAllUser(Long userNumber, Criteria criteria) {
        if (userNumber == null) {
            throw new IllegalArgumentException("유저 넘버 없음 !! ");
        }

        return consultingMapper.selectAllUser(userNumber,  criteria);
    }


    public void modify(ConsultingDto consultingDto) {
        if (consultingDto == null) {
            throw new IllegalArgumentException("게시글 수정 매개변수 null임");
        }
        consultingMapper.update(consultingDto);
    }


    public void modifyCondition(ConsultingRequestDto consultingRequestDto) {
        if (consultingRequestDto == null) {
            throw new IllegalArgumentException("consultingRequestDto 없음");
        }
        consultingMapper.updateCondition(consultingRequestDto);
    }





    //    삭제
    public void remove(Long consultingNumber) {
        if (consultingNumber == null) {
            throw new IllegalArgumentException("존재하지 않는 게시물");

        }
        consultingMapper.delete(consultingNumber);
    }

//    조회

    /**
     * @param consultingNumber
     * @return
     * @throws IllegalArgumentException 게시물 번호가 존재하지 않으면 발생된다.
     */
    public SendConsultingVo findConsulting(Long consultingNumber) {
        if (consultingNumber == null) {
            throw new IllegalArgumentException("게시물 번호가 없습니다.");
        }
        return Optional.ofNullable(consultingMapper.select(consultingNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 게시물 번호");
                });
    }

    //    전체 조회
    @Transactional(readOnly = true)
    public List<SendConsultingVo> findAll(Long consultingRequestNumber,
                                          Criteria criteria) {
        return consultingMapper.selectAll(consultingRequestNumber, criteria);
    }

    //전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal() {
        return consultingMapper.selectTotal();
    }


    // 식단전체 리스트를 일별리스트로 바꿔 주ㄱl
    public Map<String, List<DayMealVo>> getDayMealList(ConsultingRequestVo consultingRequestVo) {
        if (consultingRequestVo == null) {
            throw new IllegalArgumentException("consultingRequestDto 없음 ! ");
        }

        return Optional.ofNullable(MealUtil.getMealList(consultingMapper.getMealList(consultingRequestVo)))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 유저");
                });
    }

    // 식단 하나 눌렀을때 정보 가져오는 메소드
    public ReadMealVo readMeal(Long boardNumber) {
        if (boardNumber == null) {
            throw new IllegalArgumentException("boardNumber 없음 !");
        }
        ReadMealVo readMealVo = Optional.ofNullable(mealMapper.getMeal(boardNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 게시글");
                });

        List<FoodDto> foodList = mealMapper.getFoods(boardNumber);
        int totalKcal = 0;
        for (FoodDto food : foodList) {
            totalKcal += Integer.parseInt(food.getFoodKcal());
        }


        List<BoardFileDto> files = mealMapper.getFiles(boardNumber);

        readMealVo.setFoodList(foodList);
        readMealVo.setMealTotalKcal(totalKcal);
        readMealVo.setFiles(files);

        System.out.println(readMealVo);

        return readMealVo;
    }

    public ConsultingRequestVo selectRequestOne(Long consultingRequestNumber) {
        if (consultingRequestNumber == null) {
            throw new IllegalArgumentException("컨설팅 신청 게시글 번호 없음 ! ");
        }
        return Optional.ofNullable(consultingMapper.selectRequestOne(consultingRequestNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 컨설팅 신청 게시글 !");
                });
    }

}


