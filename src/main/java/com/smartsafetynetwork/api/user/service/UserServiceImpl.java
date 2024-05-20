package com.smartsafetynetwork.api.user.service;

import com.smartsafetynetwork.api.common.component.RandomPasswordGenerator;
import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.component.SendPasswordResetEmail;
import com.smartsafetynetwork.api.common.enums.Role;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.user.dto.UserSignUpDto;
import com.smartsafetynetwork.api.user.dto.response.UserInfoDto;
import com.smartsafetynetwork.api.user.model.User;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.user.dto.request.UserRequestDto;
import com.smartsafetynetwork.api.common.enums.SignType;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RandomPasswordGenerator randomPasswordGenerator;
    private final SendPasswordResetEmail sendPasswordResetEmail;
    private final JwtInfo jwtInfo;

    @Override
    public ResponseDto signup(UserSignUpDto userSignUpDto) {
        checkDuplicate(userSignUpDto.getUsername(), userSignUpDto.getEmail(), userSignUpDto.getPhone());

        User user = User.builder()
                .username(userSignUpDto.getUsername())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .name(userSignUpDto.getName())
                .phone(userSignUpDto.getPhone())
                .email(userSignUpDto.getEmail())
                .role(Role.ROLE_USER)
                .type(SignType.SSN)
                .build();

        userRepository.save(user);
        return new ResponseDto(HttpStatus.OK.value(), "회원가입에 성공하셨습니다.");
    }

    @Transactional
    @Override
    public ResponseDto modify(UserRequestDto userRequestDto) {
        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        checkDuplicate(userRequestDto.getUsername(), userRequestDto.getEmail(), userRequestDto.getPhone());
        user.update(userRequestDto.getEmail(), userRequestDto.getPhone());

        return new ResponseDto(HttpStatus.OK.value(), "회원 정보 수정이 완료 되었습니다.");
    }

    @Override
    public UserInfoDto info() {
        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        return UserInfoDto.builder()
                .name(user.getName())
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

        sendPasswordResetEmail.sendPasswordResetEmail(user.getEmail(), password);

        user.changedPasswordByEmail(passwordEncoder.encode(password));
        userRepository.save(user);

        return new ResponseDto(HttpStatus.OK.value(), "메일 발송이 완료되었습니다.");
    }

    private void checkDuplicate(String username, String email, String phone) {
        if (userRepository.existsByUsername(username)) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 아이디 입니다.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 이메일 입니다.");
        }

        if (userRepository.existsByPhone(phone)) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 전화번호 입니다.");
        }
    }
}