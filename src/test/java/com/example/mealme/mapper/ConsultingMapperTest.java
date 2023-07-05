package com.example.mealme.mapper;

import com.example.mealme.dto.ConsultingDto;
import com.example.mealme.vo.ConsultingVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class ConsultingMapperTest {

    @Autowired
    private ConsultingMapper consultingMapper;
    private ConsultingVo consultingVo;

    @BeforeEach
    void setUp() {
        consultingVo = new ConsultingVo();
        consultingVo.setConsultingComment("이 말이 왜 안들어갈??a?DAdad/a??까 ?");
        consultingVo.setConsultingNumber(1);
        consultingVo.setUserNumber(1);
        consultingVo.setCompanyNumber(1);
    }


    @Test
    public void insert() {
        consultingMapper.insert(consultingVo);
    }

    @Test
    void select() {
        ConsultingVo consultingVo = consultingMapper.select(1L);

        log.info(consultingVo.toString());


    }

    @Test
void selectAll(){
            List<ConsultingVo> consultingVoList=consultingMapper.selectAll();

            for (int i=0; i<consultingVoList.size(); i++){
                log.info(consultingVoList.get(i).toString());
            }
            for(ConsultingVo consultingVo : consultingVoList){
                log.info(consultingVo.toString());
            }
            consultingVoList.stream().forEach(memberDto ->log.info(memberDto.toString()));
        }










    }

