package com.smartsafetynetwork.api.service.user;

import com.smartsafetynetwork.api.component.RandomPasswordGenerator;
import com.smartsafetynetwork.api.component.JWTInfo;
import com.smartsafetynetwork.api.component.JWTProvider;
import com.smartsafetynetwork.api.enums.Role;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.domain.User;
import com.smartsafetynetwork.api.enums.Error;
import com.smartsafetynetwork.api.dto.user.request.UserRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.dto.user.response.UserResponseDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomPasswordGenerator randomPasswordGenerator;
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;
    private final JWTProvider jwtProvider;
    private final JWTInfo jwtInfo;

    @Override
    public ResponseDto signup(UserRequestDto userRequestDto) {
        if (userRepository.existsByUsername(userRequestDto.getUsername())) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 아이디 입니다.");
        }

        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 이메일 입니다.");
        }

        if (userRepository.existsByPhone(userRequestDto.getPhone())) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 전화번호 입니다.");
        }
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .name(userRequestDto.getName())
                .gender(userRequestDto.getGender())
                .birthday(userRequestDto.getBirthday())
                .phone(userRequestDto.getPhone())
                .email(userRequestDto.getEmail())
                .role(Role.ROLE_USER)
                .build();


        userRepository.save(user);
        return new ResponseDto(HttpStatus.OK.value(), "회원가입에 성공하셨습니다.");
    }

    @Override
    public CommonLoginResponseDto login(CommonLoginRequestDto commonLoginRequestDto) {
        User user = userRepository.findByUsername(commonLoginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 사용자 계정입니다."));

        if (passwordEncoder.matches(commonLoginRequestDto.getPassword(), user.getPassword())) {
            String token = jwtProvider.create(user.getId());
            return new CommonLoginResponseDto(HttpStatus.OK.value(), "로그인에 성공하셨습니다.", token, 3600, user.getRole());
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage());
        }
    }

    @Override
    public ResponseDto modify(UserRequestDto userRequestDto) {
        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        user.update(userRequestDto.getEmail(), userRequestDto.getPhone());
        userRepository.save(user);

        return new ResponseDto(HttpStatus.OK.value(), "회원 정보 수정이 완료 되었습니다.");
    }

    @Override
    public UserResponseDto info() {
        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        return UserResponseDto.builder()
                .name(user.getName())
                .birthday(user.getBirthday())
                .gender(user.getGender())
                .email(user.getEmail())
                .phone(user.getPhone())
                .status(HttpStatus.OK.value())
                .message("사용자 정보 조회에 성공하셨습니다.")
                .build();
    }

    @Override
    public ResponseDto delete() {
        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));
        userRepository.deleteById(user.getId());
        return new ResponseDto(HttpStatus.OK.value(), "회원 탈퇴가 완료 되었습니다.");
    }

    @Override
    public ResponseDto sendMailPassword(UserRequestDto userRequestDto) {
        User user = userRepository.findByUsername(userRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        if (!user.getEmail().equals(userRequestDto.getEmail())) {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자의 이메일 주소가 일치하지 않습니다.");
        }

        String password = randomPasswordGenerator.generateRandomPassword();

        sendPasswordResetEmail(user.getEmail(), password);

        user.changedPasswordByEmail(passwordEncoder.encode(password));
        userRepository.save(user);

        return new ResponseDto(HttpStatus.OK.value(), "메일 발송이 완료되었습니다.");
    }

    private void sendPasswordResetEmail(String email, String password) {
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("[스치넷] 임시 비밀번호 발급");
        simpleMailMessage.setFrom("daishi7462@naver.com");
        simpleMailMessage.setText("회원님의 임시 비밀번호는 " + password + " 입니다.");

        javaMailSender.send(simpleMailMessage);
    }
}