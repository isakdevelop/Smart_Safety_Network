package com.smartsafetynetwork.api.oauth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class CustomOauth2User implements OAuth2User {
    private final OauthLoginDto oauthLoginDto;

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return oauthLoginDto.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oauthLoginDto.getName();
    }

    public String getUsername() {
        return oauthLoginDto.getUsername();
    }
}
