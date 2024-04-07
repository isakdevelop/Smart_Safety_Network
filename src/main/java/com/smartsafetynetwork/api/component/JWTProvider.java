package com.smartsafetynetwork.api.component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTProvider {
    @Value("${secret-key}")
    private String secretKey;

    public String create(String id) {
        Instant expiredDate = Instant.now().plus(3, ChronoUnit.HOURS);
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .setSubject(id)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expiredDate))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    public String validate(String jwt) {
        String subject;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            subject = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody()
                    .getSubject();

        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return subject;
    }
}
