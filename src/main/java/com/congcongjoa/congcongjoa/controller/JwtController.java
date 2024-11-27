package com.congcongjoa.congcongjoa.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JwtController {

    @PostMapping("/auth/refresh-token")
    @Operation(summary = "엑세스 토큰 요청", description = "refresh token을 사용해 새로운 access token을 요청")
    public void refreshAccessToken() {

    }


}
