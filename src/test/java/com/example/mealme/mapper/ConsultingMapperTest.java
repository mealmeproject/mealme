package com.example.mealme.mapper;

import com.example.mealme.vo.SendConsultingVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Slf4j
class ConsultingMapperTest {

    @Autowired
    private ConsultingMapper consultingMapper;
    private SendConsultingVo sendConsultingVo;

    @BeforeEach
    void setUp() {
        sendConsultingVo = new SendConsultingVo();
        sendConsultingVo.setConsultingComment("이 말이 왜 안들어갈??a?DAdad/a??까 ?");
        sendConsultingVo.setConsultingNumber(1);
        sendConsultingVo.setUserNumber(1);
        sendConsultingVo.setCompanyNumber(1);
    }


    @Test
    public void insert() {
        consultingMapper.insert(sendConsultingVo);
    }

    @Test
    void select() {
        SendConsultingVo sendConsultingVo = consultingMapper.select(1L);

        log.info(sendConsultingVo.toString());


    }

    @Test
void selectAll(){
            List<SendConsultingVo> consultingVoList=consultingMapper.selectAll();

            for (int i=0; i<consultingVoList.size(); i++){
                log.info(consultingVoList.get(i).toString());
            }
            for(SendConsultingVo consultingVo : consultingVoList){
                log.info(consultingVo.toString());
            }
            consultingVoList.stream().forEach(memberDto ->log.info(memberDto.toString()));
        }










    }

