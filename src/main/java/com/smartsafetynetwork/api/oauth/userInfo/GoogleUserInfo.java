package com.smartsafetynetwork.api.oauth.userInfo;

import java.util.Map;

public class GoogleUserInfo extends Oauth2UserInfo {

    public GoogleUserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getMobile() {
        return (String) attributes.get("mobile");
    }
}
