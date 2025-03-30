package com.congcongjoa.congcongjoa.jwt.filter;


import com.congcongjoa.congcongjoa.dto.custom.CustomUserAdminDetails;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.jwt.JwtProvider;
import com.congcongjoa.congcongjoa.service.custom.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AdminUserLoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final String ADMIN_LOGIN_URL = "/api/public/admin/login";
    private static final String USER_LOGIN_URL = "/api/public/user/login";
    private final TokenService tokenService;
    private final JwtProvider jwtProvider;


    public AdminUserLoginFilter(TokenService tokenService, JwtProvider jwtProvider) {
        this.tokenService = tokenService;
        this.jwtProvider = jwtProvider;
        setFilterProcessesUrl("/api/public/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String requestBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            Map requestMap = mapper.readValue(requestBody, Map.class);

            String requestURL = request.getRequestURI();
            String username = (String) requestMap.get("username");
            String password = (String) requestMap.get("password");

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
            return authenticateByUrl(requestURL, authRequest);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) {
        try {
            CustomUserAdminDetails customUserAdminDetails = (CustomUserAdminDetails) authentication.getPrincipal();
            String username = customUserAdminDetails.getUsername();

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            GrantedAuthority auth = iterator.next();
            String role = auth.getAuthority();

            // 토큰 생성 시 필요한 매핑 정보를 설정
            Map<String, Object> tokenData = new HashMap<>();
            tokenData.put("username", username);
            tokenData.put("role", role);

            // JWT 토큰 생성
            String accessToken = jwtProvider.getAccessToken(tokenData);
            String refreshToken = jwtProvider.getRefreshToken(tokenData);

            // **블랙리스트 검사**
            if (tokenService.isTokenBlacklisted(refreshToken)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json;charset=UTF-8");

                // JSON 응답 작성
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonResponse = objectMapper.writeValueAsString(ResponseCode.UNAUTHORIZED.toRsData(null));
                response.getWriter().write(jsonResponse);
                return;
            }

            // 응답 설정
            response.setStatus(HttpStatus.OK.value());
            response.setContentType("application/json;charset=UTF-8");

            // Secure, HttpOnly 쿠키에 refreshAccessToken 저장
            Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(false);  // 개발 후 https 보안이 적용되면 true로 변경
            refreshTokenCookie.setDomain("localhost");
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge(24 * 60 * 60);
            response.addCookie(refreshTokenCookie);

            tokenService.saveAdminUserToken(username, refreshToken);

            //ResponseData 사용하여 RsData 객체 생성 및 JSON 응답 작성
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("accessToken", accessToken);  // accessToken 포함

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(ResponseCode.USER_LOGIN_SUCCESS.toRsData(responseData));

            response.getWriter().write(jsonResponse);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        // ResponseCode를 사용하여 RsData 객체 생성 및 JSON 응답 작성
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(ResponseCode.UNAUTHORIZED.toRsData(null));

        response.getWriter().write(jsonResponse);
    }

    private Authentication authenticateByUrl(String requestURL, UsernamePasswordAuthenticationToken authRequest) {
        return switch (requestURL) {
            case ADMIN_LOGIN_URL -> authenticate(authRequest, "ROLE_ADMIN");
            case USER_LOGIN_URL -> authenticate(authRequest, "ROLE_USER");
            default -> null;
        };
    }

    private Authentication authenticate(UsernamePasswordAuthenticationToken authRequest, String role) {
        String username = authRequest.getName();
        String password = (String) authRequest.getCredentials();

        // 1. 이미 인증을 처리하는 메서드
        UserDetails userDetails = tokenService.authenticateUserAdmin(username, password, role);

        if (userDetails == null) {
            return null;
        }

        // 2. 인증 후에는 비밀번호(null)로 설정
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}