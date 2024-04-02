package com.smartsafetynetwork.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.smartsafetynetwork.api.dto.user.request.UserSignupRequestDto;
import com.smartsafetynetwork.api.repository.user.UserRepository;
import com.smartsafetynetwork.api.service.user.UserServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SmartsafetynetworkApplicationTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void test_Sign_Up_Success() {
        UserSignupRequestDto userSignupRequestDto =
                new UserSignupRequestDto("테스트 아이디", "password", "테스트 유저", "남성",
                        "1999-12-03", "test@example.com", "010-0000-0000");

        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");

        String responseMessage = userService.signup(userSignupRequestDto).getMessage();

        assertEquals("회원가입에 성공하셨습니다.", responseMessage);
    }

//    @Test
//    public void test_Login_Success() {
//        UserLoginRequestDto userLoginRequestDto = new UserLoginRequestDto("테스트 아이디", "password");
//        User user = new User();
//        user.setId(1L);
//        user.setName("Test User");
//        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches(any(), any())).thenReturn(true);
//
//        UserLoginResponseDto userLoginResponseDto = userService.login(userLoginRequestDto);
//
//        assertEquals(1L, userLoginResponseDto.getId());
//        assertEquals("테스트 성공", userLoginResponseDto.getName());
//    }
}
