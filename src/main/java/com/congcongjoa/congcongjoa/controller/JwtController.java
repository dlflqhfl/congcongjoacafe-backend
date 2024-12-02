package com.congcongjoa.congcongjoa.controller;

import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.RsData.RsData;
import com.congcongjoa.congcongjoa.service.custom.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class JwtController {

    @Autowired
    TokenService tokenService;

    @PostMapping("/auth/refresh-token")
    @Operation(summary = "엑세스 토큰 요청", description = "refresh token을 사용해 새로운 access token을 요청")
    public RsData<String> refreshAccessToken(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        try {
            if (refreshToken == null || refreshToken.isEmpty()) {
                System.out.println("안되냐?");
                return ResponseCode.UNAUTHORIZED.toRsData(null); // refreshToken이 없으면 401 응답
            }

            System.out.println(refreshToken + " 제발좀도ㅚ주라");
            // 1. 리프레시 토큰 검증 로직 추가

            // 2. 새로운 액세스 토큰 생성
            String newAccessToken = "newAccessToken"; // 새로운 액세스 토큰 생성 로직

            // 요청이 성공적으로 처리되었음을 알리고 새로운 액세스 토큰 응답
            return ResponseCode.OK.toRsData(newAccessToken);
        } catch (Exception e) {
            // 예외 발생 시 적절한 오류 코드로 응답
            return ResponseCode.UNAUTHORIZED.toRsData(null);
        }
    }

    @PostMapping("/owner/logout")
    public RsData<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 쿠키 삭제
        Cookie[] cookies = request.getCookies();

        System.out.println(Arrays.toString(cookies) + "하이");
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
}