package com.example.mealme.mapper;

import com.example.mealme.service.company.ConsultingReplyService;
import com.example.mealme.vo.ConsultingReplyVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Slf4j
class ConsultingReplyMapperTest {

    @Autowired
    private  ConsultingReplyMapper consultingReplyMapper;
    private ConsultingReplyVo consultingReplyVo;
    @BeforeEach

   void setUp(){
        ConsultingReplyVo consultingReplyVo=new ConsultingReplyVo();
                consultingReplyVo=new ConsultingReplyVo();
                consultingReplyVo.setReplyNumber(1L);
                consultingReplyVo.setReplyContent("잘 들어갑니까?");

    }

    @Test
    void insert() {
    }

    @Test
    void select() {

    }

    @Test
    void selectAll() {
    }
}