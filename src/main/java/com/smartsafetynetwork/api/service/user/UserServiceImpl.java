package com.smartsafetynetwork.api.service.user;

import com.smartsafetynetwork.api.common.RandomPasswordGenerator;
import com.smartsafetynetwork.api.common.RequestId;
import com.smartsafetynetwork.api.common.ResponseMessage;
import com.smartsafetynetwork.api.domain.User;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.user.request.UserMailRequestDto;
import com.smartsafetynetwork.api.dto.user.request.UserModifyRequestDto;
import com.smartsafetynetwork.api.dto.user.request.UserSignupRequestDto;
import com.smartsafetynetwork.api.dto.user.request.UserLoginRequestDto;
import com.smartsafetynetwork.api.dto.user.response.UserInfoResponseDto;
import com.smartsafetynetwork.api.dto.user.response.UserLoginResponseDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;

    @Override
    public ResponseMessage signup(UserSignupRequestDto userSignupRequestDto) {
        if (userRepository.existsByUsername(userSignupRequestDto.getUsername())) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 아이디 입니다.");
        }

        if (userRepository.existsByEmail(userSignupRequestDto.getEmail())) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 이메일 입니다.");
        }

        if (userRepository.existsByPhone(userSignupRequestDto.getPhone())) {
            throw new CustomException(Error.CONFLICT.getStatus(), "이미 존재하는 전화번호 입니다.");
        }
        User user = User.builder()
                .username(userSignupRequestDto.getUsername())
                .password(passwordEncoder.encode(userSignupRequestDto.getPassword()))
                .name(userSignupRequestDto.getName())
                .gender(userSignupRequestDto.getGender())
                .birthday(userSignupRequestDto.getBirthday())
                .phone(userSignupRequestDto.getPhone())
                .email(userSignupRequestDto.getEmail())
                .build();

        if (userRepository.findByUsername(userSignupRequestDto.getUsername()).isEmpty()) {
            userRepository.save(user);
            return new ResponseMessage(0, "회원가입에 성공하셨습니다.");
        } else {
            throw new CustomException(Error.CONFLICT.getStatus(), Error.CONFLICT.getMessage());
        }
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByUsername(userLoginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            return new UserLoginResponseDto(user.getId(), user.getName());
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage());
        }
    }

    @Override
    public ResponseMessage modify(UserModifyRequestDto userModifyRequestDto) {
        User user = userRepository.findById(userModifyRequestDto.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        user.update(userModifyRequestDto.getAfterEmail(), userModifyRequestDto.getAfterPhone());
        userRepository.save(user);

        return new ResponseMessage(0, "회원 정보 수정이 완료 되었습니다.");
    }

    @Override
    public UserInfoResponseDto info(RequestId requestId) {
        User user = userRepository.findById(requestId.getId())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        return new UserInfoResponseDto(user.getName(), user.getBirthday(), user.getGender(), user.getEmail(),
                user.getPhone());
    }

    @Override
    public ResponseMessage delete(RequestId requestId) {
        User user = userRepository.findById(requestId.getId())
                        .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));
        userRepository.deleteById(user.getId());
        return new ResponseMessage(200, "회원 탈퇴가 완료 되었습니다.");
    }

    @Override
    public ResponseMessage sendMailPassword(UserMailRequestDto userMailRequestDto) {
        User user = userRepository.findByUsername(userMailRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage()));

        if (!user.getEmail().equals(userMailRequestDto.getEmail())) {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "사용자의 이메일 주소가 일치하지 않습니다.");
        }

        String password = RandomPasswordGenerator.generateRandomPassword();

        sendPasswordResetEmail(user.getEmail(), password);

        user.changedPasswordByEmail(passwordEncoder.encode(password));
        userRepository.save(user);

        return new ResponseMessage(0, "메일 발송이 완료되었습니다.");
    }

    private void sendPasswordResetEmail(String email, String password) {
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("[스치넷] 임시 비밀번호 발급");
        simpleMailMessage.setFrom("daishi7462@naver.com");
        simpleMailMessage.setText("회원님의 임시 비밀번호는 " + password + " 입니다.");

        javaMailSender.send(simpleMailMessage);
    }
}