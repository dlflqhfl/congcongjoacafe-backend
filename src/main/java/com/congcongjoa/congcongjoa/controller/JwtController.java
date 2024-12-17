package com.congcongjoa.congcongjoa.controller;

import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.jwt.JwtProvider;
import com.congcongjoa.congcongjoa.service.custom.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    TokenService tokenService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/auth/refresh-token")
    @Operation(summary = "엑세스 토큰 요청", description = "refresh token을 사용해 새로운 access token을 요청")
    public RsData<Map<String, String>>  refreshAccessToken(@CookieValue(value = "refreshToken") String refreshToken) {

        if (!isValidRefreshToken(refreshToken)) {
            return unauthorizedResponse();
        }

        try {
            String newAccessToken = tokenService.createAccessTokenFromRefreshToken(refreshToken);
            return ResponseCode.OK.toRsData(Map.of("accessToken", newAccessToken));
        } catch (Exception e) {
            return unauthorizedResponse();
        }
    }

    @PostMapping("/owner/logout")
    @Operation(summary = "오너 로그아웃", description = "refreshToken을 레디스 블랙리스트에 저장 후 토큰 제거")
    public RsData<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키 삭제
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {

                    //토큰 블랙리스트
                    tokenService.invalidateToken(cookie.getValue());

                    // Clear Cookie
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return ResponseCode.OK.toRsData("logout success");
    }

    private boolean isValidRefreshToken(String refreshToken) {
        return refreshToken != null && !refreshToken.isEmpty();
    }

    private RsData<Map<String, String>> unauthorizedResponse() {
        return ResponseCode.UNAUTHORIZED.toRsData(null);
    }
}