package com.example.mealme.service.user;

import com.example.mealme.dto.UserDto;
import com.example.mealme.mapper.UserMapper;
import com.example.mealme.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;

    // 회원 등록
    public void register(UserDto userDto){
        if (userDto == null){throw new IllegalArgumentException("회원정보 누락 !");}

        String userPassword;
        userPassword = Util.pwSha256(userDto.getUserPassword());
        userDto.setUserPassword(userPassword);
        System.out.println("userPassword ======= " + userPassword);
        userMapper.insert(userDto);
    }

    /**
     * 회원 번호 조회하기 (아이디, 패스워드)
     * @param userId
     * @param userPassword
     * @return
     * @throws IllegalArgumentException 존재하지 않는 회원 id, pw로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public Long findUserNumber(String userId, String userPassword){
        if (userId == null || userPassword == null){ throw new IllegalArgumentException("id, password 누락 !");}

        String userPw = Util.pwSha256(userPassword);

        return Optional.ofNullable(userMapper.selectUserNumber(userId, userPw))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 회원입니다. ");});
    }
}
