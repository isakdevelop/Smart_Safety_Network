package com.smartsafetynetwork.api.common.component;

import com.smartsafetynetwork.api.common.enums.Error;
import com.smartsafetynetwork.api.common.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtProvider {
    @Value("${secret-key}")
    private String secretKey;
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    public String create(String id) {
        Instant expiredDate = Instant.now().plus(3, ChronoUnit.HOURS);
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .header().add("type", "jwt").and()
                .subject("access-token")
                .claim("id", id)
                .signWith(key)
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(expiredDate))
                .compact();
    }

    public String validate(String jwt) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(jwt);

            return claims.getPayload().get("id", String.class);

        } catch (Exception exception) {
            throw new CustomException(Error.FORBIDDEN.getStatus(), "토큰에 대한 접근 권한이 존재하지 않습니다!");
        }
    }

    public void addSecurityHeaders(HttpServletResponse response) {
        response.addHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");
        response.addHeader("X-Content-Type-Options", "nosniff");
        response.addHeader("X-Frame-Options", "DENY");
        response.addHeader("X-XSS-Protection", "1; mode=block");
        response.addHeader("Content-Security-Policy", "default-src 'self'");
    }

    public String extractTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
