package com.smartsafetynetwork.api.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartsafetynetwork.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2LoginServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

//        User user = null;
//        String id = "";

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        } catch (Exception exception) {
            exception.printStackTrace();
        }

//        if (oauthClientName.equals("kakao")) {
//            id = "kakao_" + oAuth2User.getAttributes().get("id");
//            user = User.builder()
//                    .id(id)
//                    .username("kakao_" + oAuth2User.getAttributes().get("name"))
//                    .name((String) oAuth2User.getAttributes().get("name"))
//                    .password("password")
//                    .email("dja")
//                    .type(SignType.KAKAO)
//                    .build();
//        }
//
//        if (oauthClientName.equals("naver")) {
//            Map<String, String> response = (Map<String, String>) oAuth2User.getAttributes().get("response");
//            id = "naver_" + response.get("id").substring(0, 14);
//            String email = response.get("email");
//            user = User.builder()
//                    .id(id)
//                    .username("naver" + oAuth2User.getAttributes().get("name"))
//                    .name((String) oAuth2User.getAttributes().get("name"))
//                    .password("password")
//                    .email("dja")
//                    .type(SignType.NAVER)
//                    .build();
//        }

//        userRepository.save(user);


        return oAuth2User;
//        return new OAuthUser(id);
    }

}
