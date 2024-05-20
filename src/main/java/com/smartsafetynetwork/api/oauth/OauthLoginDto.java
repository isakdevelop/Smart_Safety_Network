package com.smartsafetynetwork.api.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthLoginDto {
    private String role;
    private String token;
    private String name;
    private String username;
}
