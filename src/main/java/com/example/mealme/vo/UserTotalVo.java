package com.example.mealme.vo;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Data
public class UserTotalVo {
    private Long userCount;
    private Long companyCount;
    private Long totalCount;
}
