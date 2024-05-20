package com.smartsafetynetwork.api.common.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_DRIVER("ROLE_DRIVER"),
    ROLE_POLICE("ROLE_POLICE")
    ;

    private final String role;
}
