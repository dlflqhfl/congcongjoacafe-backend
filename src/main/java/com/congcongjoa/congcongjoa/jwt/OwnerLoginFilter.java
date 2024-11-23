package com.congcongjoa.congcongjoa.jwt;
import com.congcongjoa.congcongjoa.dto.custom.CustomOwnerDetails;
import com.congcongjoa.congcongjoa.enums.ResponseCode;
import com.congcongjoa.congcongjoa.service.custom.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OwnerLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final TokenService tokenService;

    public OwnerLoginFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/public/owner/login");  // 오너 로그인 URL
        this.jwtProvider = jwtProvider;
        this.tokenService = tokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            String requestBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> requestMap = mapper.readValue(requestBody, Map.class);

            String sCode = requestMap.get("sCode");
            String password = requestMap.get("password");
            String sName = requestMap.get("sName");

            System.out.println(sCode + " " + password + " " + sName);

            CustomAuthenticationToken authRequest = new CustomAuthenticationToken(sCode, password, sName);
            return authenticationManager.authenticate(authRequest);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        CustomOwnerDetails customOwnerDetails = (CustomOwnerDetails) authentication.getPrincipal();
        String sCode = customOwnerDetails.getUsername();
        boolean isFirstLogin = customOwnerDetails.isFirstLogin();

        // 로그 출력 추가
        System.out.println("CustomOwnerDetails isFirstLogin 값 (successfulAuthentication): " + isFirstLogin);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // 토큰 생성 시 필요한 매핑 정보를 설정
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("sCode", sCode);
        tokenData.put("role", role);

        // 첫 로그인일 경우에만 추가
        if (isFirstLogin) {
            tokenData.put("isFirstLogin", true);
        }

        String accessToken = jwtProvider.getAccesToken(tokenData);
        String refreshToken = jwtProvider.getRefreshToken(tokenData);

        tokenService.saveOwnerToken(sCode, accessToken, refreshToken, authentication);

        // 응답 설정
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Authorization", "Bearer " + accessToken);
        response.addHeader("refreshToken", refreshToken);

        // ResponseCode 사용하여 RsData 객체 생성 및 JSON 응답 작성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("accessToken", accessToken);
        responseData.put("refreshToken", refreshToken);
        responseData.put("isFirstLogin", isFirstLogin);


        // 로그 출력 추가
        System.out.println("Response Data: " + responseData);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(ResponseCode.USER_LOGIN_SUCCESS.toRsData(responseData));

        response.getWriter().write(jsonResponse);
    }

    private static Map<String, Object> getStringObjectMap(Authentication authentication, String sCode, boolean isFirstLogin) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // 토큰 생성 시 필요한 매핑 정보를 설정
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("sCode", sCode);
        tokenData.put("role", role);

        // 첫 로그인일 경우에만 추가
        if (isFirstLogin) {
            tokenData.put("isFirstLogin", true);
        }
        return tokenData;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");

        // ResponseCode를 사용하여 RsData 객체 생성 및 JSON 응답 작성
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(ResponseCode.UNAUTHORIZED.toRsData(null));

        response.getWriter().write(jsonResponse);
    }
}