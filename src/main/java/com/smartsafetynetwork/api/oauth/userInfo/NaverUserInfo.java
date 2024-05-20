package com.smartsafetynetwork.api.oauth.userInfo;

import java.util.Map;

public class NaverUserInfo extends Oauth2UserInfo {

    public NaverUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getId() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response == null ? null : (String) response.get("id");
    }

    @Override
    public String getName() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response == null ? null : (String) response.get("name");
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response == null ? null : (String) response.get("email");
    }

    @Override
    public String getMobile() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return response == null ? null : (String) response.get("mobile");
    }
}
