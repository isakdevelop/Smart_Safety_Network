package com.smartsafetynetwork.api.auth.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@RedisHash(value = "email", timeToLive = 300)
public class Certification {
    @Id
    private String email;
    private String certificationNumber;
}
