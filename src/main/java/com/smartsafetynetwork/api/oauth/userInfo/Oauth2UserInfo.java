package com.smartsafetynetwork.api.oauth.userInfo;

import java.util.Map;

public abstract class Oauth2UserInfo {
    protected Map<String, Object> attributes;
    public Oauth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    public abstract String getProvider();
    public abstract String getId();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getMobile();
}
