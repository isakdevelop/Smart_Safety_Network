package com.smartsafetynetwork.api.auth.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "refreshToken", timeToLive = 18000)
public class RefreshToken {
    @Id
    private String refreshToken;
    private String requestId;
}
