package com.smartsafetynetwork.api.oauth.service;

import com.smartsafetynetwork.api.oauth.userInfo.GoogleUserInfo;
import com.smartsafetynetwork.api.oauth.userInfo.KakaoUserInfo;
import com.smartsafetynetwork.api.oauth.userInfo.NaverUserInfo;
import com.smartsafetynetwork.api.oauth.CustomOauth2User;
import com.smartsafetynetwork.api.oauth.OauthLoginDto;
import com.smartsafetynetwork.api.common.component.JwtProvider;
import com.smartsafetynetwork.api.common.enums.Role;
import com.smartsafetynetwork.api.common.enums.SignType;
import com.smartsafetynetwork.api.common.exception.CustomException;
import com.smartsafetynetwork.api.oauth.userInfo.Oauth2UserInfo;
import com.smartsafetynetwork.api.user.model.User;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        log.info(oAuth2User);

        String registrationId = request.getClientRegistration().getRegistrationId();
        Oauth2UserInfo oauth2UserInfo;

        if (registrationId.equals("google")) {
            oauth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (registrationId.equals("naver")) {
            oauth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        } else if (registrationId.equals("kakao")) {
            oauth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        } else {
            throw new CustomException(HttpStatus.NOT_FOUND.value(), "지원하지 않는 소셜 로그인입니다.");
        }

        if(userRepository.existsByPhone(oauth2UserInfo.getMobile())) {
            throw new CustomException(HttpStatus.CONFLICT.value(), "이미 존재하는 회원입니다.");
        }

        User oauthUser = userRepository.findByEmail(oauth2UserInfo.getEmail()).orElseGet(() -> {
            User user = User.builder()
                    .id(oauth2UserInfo.getId())
                    .username(oauth2UserInfo.getProvider() + "_" + UUID.randomUUID())
                    .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                    .email(oauth2UserInfo.getEmail())
                    .name(oauth2UserInfo.getName())
                    .phone(oauth2UserInfo.getMobile())
                    .role(Role.ROLE_USER)
                    .type(getSignType(oauth2UserInfo.getProvider()))
                    .build();
            return userRepository.save(user);
        });

        String token = jwtProvider.createAccessToken(oauthUser.getUsername(), oauthUser.getRole());

        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());
        attributes.put("token", token);

        OauthLoginDto oauthLoginDto = OauthLoginDto.builder()
                .role("ROLE_USER")
                .name(oauth2UserInfo.getName())
                .username(oauth2UserInfo.getProvider() + "_" + UUID.randomUUID())
                .token(token)
                .build();

        return new CustomOauth2User(oauthLoginDto);
    }

    private SignType getSignType(String provider) {
        return switch (provider.toLowerCase()) {
            case "google" -> SignType.GOOGLE;
            case "naver" -> SignType.NAVER;
            case "kakao" -> SignType.KAKAO;
            default -> throw new CustomException(HttpStatus.NOT_FOUND.value(), "지원하지 않는 소셜 로그인입니다.");
        };
    }
}
