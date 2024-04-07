package com.smartsafetynetwork.api.service.auth;

import com.smartsafetynetwork.api.common.RandomPasswordGenerator;
import com.smartsafetynetwork.api.domain.Certification;
import com.smartsafetynetwork.api.domain.value.Error;
import com.smartsafetynetwork.api.dto.ResponseDto;
import com.smartsafetynetwork.api.dto.auth.request.AuthMailRequestDto;
import com.smartsafetynetwork.api.exception.CustomException;
import com.smartsafetynetwork.api.repository.certification.CertificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final CertificationRepository certificationRepository;
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;

    @Override
    public ResponseDto authCheckMail(AuthMailRequestDto authMailRequestDto) {
        String password = RandomPasswordGenerator.generateRandomPassword();
        sendPasswordResetEmail(authMailRequestDto.getEmail(), password);
        Certification certification = Certification.builder()
                .email(authMailRequestDto.getEmail())
                .certificationNumber(password)
                .build();

        certificationRepository.save(certification);

        return new ResponseDto(200, "메일 발송이 완료되었습니다.");
    }

    @Override
    public ResponseDto authMail(AuthMailRequestDto authMailRequestDto) {
        if (certificationRepository.existsByCertificationNumber(authMailRequestDto.getPassword())) {
            return new ResponseDto(200, "메일 인증이 완료되었습니다.");
        } else {
            throw new CustomException(Error.NOT_FOUND.getStatus(), "잘못된 비밀번호 입니다.");
        }
    }

    private void sendPasswordResetEmail(String email, String password) {
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("[스치넷] 이메일 인증");
        simpleMailMessage.setFrom("daishi7462@naver.com");
        simpleMailMessage.setText("인증 번호는 " + password + " 입니다.");

        javaMailSender.send(simpleMailMessage);
    }
}
