package com.smartsafetynetwork.api.auth.repository;

import com.smartsafetynetwork.api.auth.model.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
