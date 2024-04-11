package com.smartsafetynetwork.api.filter;

import com.smartsafetynetwork.api.component.JWTProvider;
import com.smartsafetynetwork.api.domain.Admin;
import com.smartsafetynetwork.api.domain.User;
import com.smartsafetynetwork.api.repository.admin.AdminRepository;
import com.smartsafetynetwork.api.repository.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTProvider jwtProvider;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    private String parseBearerToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);

        if (!hasAuthorization) {
            return null;
        }

        boolean isBearer = authorization.startsWith("Bearer ");
        if (!isBearer) {
            return null;
        }

        return authorization.substring(7);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            if(token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String id = jwtProvider.validate(token);

            if (id == null) {
                filterChain.doFilter(request, response);
                return;
            }

            Optional<User> user = userRepository.findById(id);
            Optional<Admin> admin = adminRepository.findById(id);

            String role = null;

            if (user.isPresent()) {
                role = String.valueOf(user.get().getRole());
            } else if (admin.isPresent()) {
                role = String.valueOf(admin.get().getRole());
            }

            if (role == null) {
                throw new ServletException("해당 아이디에서 역할을 찾을 수 없습니다. : " + id);
            }

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(id, null, authorities);

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            securityContext.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(securityContext);

        } catch (Exception exception) {
            throw new ServletException("JWT 인증 필터에서 예외 발생", exception);
        }

        filterChain.doFilter(request, response);
    }
}
