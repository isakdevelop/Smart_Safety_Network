package com.smartsafetynetwork.api.auth.service;

import com.smartsafetynetwork.api.auth.dto.FindMailDto;
import com.smartsafetynetwork.api.auth.dto.LogoutDto;
import com.smartsafetynetwork.api.auth.model.RefreshToken;
import com.smartsafetynetwork.api.auth.repository.RefreshTokenRepository;
import com.smartsafetynetwork.api.common.component.JwtInfo;
import com.smartsafetynetwork.api.common.component.JwtProvider;
import com.smartsafetynetwork.api.common.component.RandomPasswordGenerator;
import com.smartsafetynetwork.api.auth.model.Certification;
import com.smartsafetynetwork.api.common.component.SendPasswordResetEmail;
import com.smartsafetynetwork.api.auth.dto.LoginRequestDto;
import com.smartsafetynetwork.api.auth.dto.LoginResponseDto;
import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.common.dto.ResponseDto;
import com.smartsafetynetwork.api.auth.dto.VerifyMailDto;
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
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtInfo jwtInfo;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CertificationRepository certificationRepository;
    private final SendPasswordResetEmail sendPasswordResetEmail;
    private final RandomPasswordGenerator randomPasswordGenerator;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 사용자 계정입니다."));

        if (passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            String token = jwtProvider.createAccessToken(user.getId(), user.getRole());
            String refreshToken = jwtProvider.createRefreshToken(user.getId());
            return new LoginResponseDto(HttpStatus.OK.value(), "로그인에 성공하셨습니다.", token, refreshToken, 3600, user.getRole());
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), Error.NOT_FOUND.getMessage());
        }
    }

    @Override
    public ResponseDto logout(LogoutDto logoutDto) {
        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 사용자 입니다."));

        if (refreshTokenRepository.findById(logoutDto.getRefreshToken())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "인증 정보가 올바르지 않습니다."))
                .getRequestId().equals(user.getId())) {
            refreshTokenRepository.deleteById(logoutDto.getRefreshToken());
            return new ResponseDto(HttpStatus.OK.value(), "로그아웃이 완료되었습니다.");
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "로그아웃에 실패하였습니다.");
        }
    }

    @Override
    public ResponseDto findMail(FindMailDto findMailDto) {
        String password = randomPasswordGenerator.generateRandomPassword();
        sendPasswordResetEmail.sendPasswordResetEmail(findMailDto.getEmail(), password);
        Certification certification = Certification.builder()
                .email(findMailDto.getEmail())
                .certificationNumber(password)
                .build();

        certificationRepository.save(certification);

        return new ResponseDto(HttpStatus.OK.value(), "메일 발송이 완료되었습니다.");
    }

    @Override
    public ResponseDto verifyMail(VerifyMailDto verifyMailDto) {
        if (certificationRepository.findById(verifyMailDto.getEmail())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "잘못된 비밀번호 입니다."))
                .getCertificationNumber().equals(verifyMailDto.getPassword())) {
            return new ResponseDto(HttpStatus.OK.value(), "메일 인증이 완료되었습니다.");
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "잘못된 비밀번호 입니다.");
        }
    }

    @Override
    public LoginResponseDto generateAccessToken(RefreshToken refreshToken) {
        RefreshToken refreshtoken = refreshTokenRepository.findById(refreshToken.getRefreshToken())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 토큰 입니다."));

        User user = userRepository.findById(jwtInfo.getUserIdFromJWT())
                .orElseThrow(() -> new CustomException(Error.NOT_FOUND.getStatus(), "존재하지 않는 사용자 입니다."));

        if (refreshtoken.getRequestId().equals(user.getId())) {
            String token = jwtProvider.createAccessToken(user.getId(), user.getRole());
            return new LoginResponseDto(
                    HttpStatus.OK.value(), "토큰 재발급이 완료되었습니다.",
                    token, refreshToken.getRefreshToken(), 3600, user.getRole()
            );
        }
        throw new CustomException(Error.NOT_FOUND.getStatus(), "토큰 재발급에 실패하였습니다.");
    }
}
