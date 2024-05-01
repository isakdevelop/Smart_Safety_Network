package com.smartsafetynetwork.api.auth.service;

import com.smartsafetynetwork.api.admin.model.Admin;
import com.smartsafetynetwork.api.admin.repository.AdminRepository;
import com.smartsafetynetwork.api.common.component.JwtProvider;
import com.smartsafetynetwork.api.common.component.RandomPasswordGenerator;
import com.smartsafetynetwork.api.auth.model.Certification;
import com.smartsafetynetwork.api.common.component.SendPasswordResetEmail;
import com.smartsafetynetwork.api.common.dto.CommonLoginRequestDto;
import com.smartsafetynetwork.api.common.dto.CommonLoginResponseDto;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.auth.dto.request.AuthMailRequestDto;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.auth.repository.CertificationRepository;
import com.smartsafetynetwork.api.user.model.User;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CertificationRepository certificationRepository;
    private final SendPasswordResetEmail sendPasswordResetEmail;
    private final RandomPasswordGenerator randomPasswordGenerator;

    @Override
    public CommonLoginResponseDto userLogin(CommonLoginRequestDto commonLoginRequestDto) {
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
    public CommonLoginResponseDto adminLogin(CommonLoginRequestDto commonLoginRequestDto) {
        Admin admin = adminRepository.findByPoliceNumber(commonLoginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 경찰 번호 입니다."));

        if (passwordEncoder.matches(commonLoginRequestDto.getPassword(), admin.getPassword())) {
            String token = jwtProvider.create(admin.getId());
            return new CommonLoginResponseDto(HttpStatus.OK.value(), "로그인에 성공하셨습니다.", token, 3600, admin.getRole());
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "비밀번호가 틀렸습니다!");
        }
    }

    @Override
    public ResponseDto authCheckMail(AuthMailRequestDto authMailRequestDto) {
        String password = randomPasswordGenerator.generateRandomPassword();
        sendPasswordResetEmail.sendPasswordResetEmail(authMailRequestDto.getEmail(), password);
        Certification certification = Certification.builder()
                .email(authMailRequestDto.getEmail())
                .certificationNumber(password)
                .build();

        certificationRepository.save(certification);

        return new ResponseDto(HttpStatus.OK.value(), "메일 발송이 완료되었습니다.");
    }

    @Override
    public ResponseDto authMail(AuthMailRequestDto authMailRequestDto) {
        if (certificationRepository.existsByCertificationNumber(authMailRequestDto.getPassword())) {
            return new ResponseDto(HttpStatus.OK.value(), "메일 인증이 완료되었습니다.");
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "잘못된 비밀번호 입니다.");
        }
    }
}
