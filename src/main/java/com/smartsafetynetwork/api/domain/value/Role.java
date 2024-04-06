package com.smartsafetynetwork.api.domain.value;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DRIVER("ROLE_DRIVER")
    ;

    private final String role;
}
