package com.smartsafetynetwork.api.oauth.userInfo;

import java.util.Map;

public class KakaoUserInfo extends Oauth2UserInfo {

    public KakaoUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getName() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        return account == null ? null : (String) account.get("name");
    }

    @Override
    public String getEmail() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        return account == null ? null : (String) account.get("email");
    }

    @Override
    public String getMobile() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        return account == null ? null : (String) account.get("phone_number");
    }
}
