package com.example.mealme.service;

import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.AdminMapper;
import com.example.mealme.service.admin.AdminService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
@Transactional
public class AdminServiceTest {
    @Mock
    private AdminMapper adminMapper;

    @InjectMocks
    private AdminService adminService;

    private UserDto userDto;


}
