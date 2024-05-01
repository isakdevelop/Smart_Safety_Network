package com.smartsafetynetwork.api.common.component;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendPasswordResetEmail {
    private final JavaMailSender javaMailSender;
    private final SimpleMailMessage simpleMailMessage;
    public void sendPasswordResetEmail(String email, String password) {
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("[스치넷] 이메일 인증");
        simpleMailMessage.setFrom("daishi7462@naver.com");
        simpleMailMessage.setText("인증 번호는 " + password + " 입니다.");

        javaMailSender.send(simpleMailMessage);
    }
}
