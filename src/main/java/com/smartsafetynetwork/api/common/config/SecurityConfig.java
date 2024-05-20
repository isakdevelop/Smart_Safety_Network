package com.smartsafetynetwork.api.common.config;

import com.smartsafetynetwork.api.oauth.service.CustomOAuth2UserService;
import com.smartsafetynetwork.api.common.component.CustomAuthenticationEntryPoint;
import com.smartsafetynetwork.api.common.filter.JwtAuthenticationFilter;
import com.smartsafetynetwork.api.oauth.handler.Oauth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CorsConfigurationSource corsConfigurationSource;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers(headers -> headers
                        .xssProtection(XssConfig -> XssConfig.headerValue(HeaderValue.ENABLED_MODE_BLOCK)))
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                "/", "/api/auth/login", "/api/auth/token", "/favicon.ico", "/error", "/oauth2/authorization/**",
                                "/api/auth/findMail", "/api/auth/verifyMail", "/auth/oauth2/authorization/**", "/login/oauth2/code/**",
                                "/api/user/signup", "/api/user/mail")
                        .permitAll()
                        .requestMatchers(
                                "/api/police/authority"
                        ).hasRole("USER")
                        .requestMatchers(
                                "/api/police/info", "/api/police/modify", "/api/criminal/write", "/api/criminal/modify",
                                "/api/criminal/delete",
                                "/api/mp/write", "/api/mp/modify", "/api/mp/delete", "/api/cb/list"
                        ).hasRole("POLICE")
                        .requestMatchers(
                                "/api/user/info", "/api/user/modify", "/api/user/delete",
                                "/api/auth/token", "/api/auth/logout"
                        ).hasAnyRole("USER", "POLICE")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .authorizationEndpoint(endpoint -> endpoint.baseUri("/oauth2/authorization/")
                        )
                        .redirectionEndpoint(endpoint -> endpoint
                                .baseUri("/login/oauth2/code/*")
                        )
                        .userInfoEndpoint(endpoint -> endpoint
                                .userService(customOAuth2UserService)
                        )
                        .successHandler(oauth2LoginSuccessHandler)
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                );

        return httpSecurity.build();
    }
}
