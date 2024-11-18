package com.congcongjoa.congcongjoa.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {

    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception{
        http
            .securityMatcher("/api/**") 
            .authorizeHttpRequests(
                authorizeHttpRequests -> authorizeHttpRequests
                    .requestMatchers("/api/*","/api/member/**").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/member/login").permitAll()
                    .requestMatchers(HttpMethod.POST,"/api/member/logout").permitAll()
                    .anyRequest().authenticated()
            )
            .csrf(
                csrf -> csrf.disable()
            )
            .httpBasic(
                httpBasic -> httpBasic.disable()
            ) 
            .formLogin(
                formLogin -> formLogin.disable()
            )
            .sessionManagement(
                sessionManagement -> sessionManagement.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthorizationFilter,
             UsernamePasswordAuthenticationFilter.class
             );
        return http.build();
    }
    
}
