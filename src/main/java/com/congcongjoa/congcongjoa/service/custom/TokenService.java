package com.congcongjoa.congcongjoa.service.custom;

import com.congcongjoa.congcongjoa.dto.custom.CustomOwnerDetails;
import com.congcongjoa.congcongjoa.entity.redis.RefreshToken;
import com.congcongjoa.congcongjoa.entity.Store;
import com.congcongjoa.congcongjoa.enums.StoreStatus;
import com.congcongjoa.congcongjoa.jwt.JwtProvider;
import com.congcongjoa.congcongjoa.repository.RefreshTokenRepository;
import com.congcongjoa.congcongjoa.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JwtProvider jwtProvider;


    //owner jwt로그인 인증
    public UserDetails findUserBySNameAndPasswordAndSCode(String sName, String password, String sCode) {
        Store store = storeRepository.findBySNameAndSCode(sName, sCode);

        if (store == null || !isPasswordValid(store, password)) {
            return null;
        }

        return new CustomOwnerDetails(store.getSCode(), store.getSPw());
    }

    @Transactional
    public void saveOwnerToken(String sCode, String refreshToken, Authentication authentication) {
        Store store = storeRepository.findBysCode(sCode);

        // Store 상태를 확인하여 isFirstLogin 값 설정
        boolean isFirstLogin = store.getSStatus() == StoreStatus.REGISTERED;



        // CustomOwnerDetails에 isFirstLogin 값 설정
        CustomOwnerDetails customOwnerDetails = (CustomOwnerDetails) authentication.getPrincipal();
        customOwnerDetails.setFirstLogin(isFirstLogin);

        // 리프래시 토큰을 Redis에 저장
        RefreshToken redis = new RefreshToken(refreshToken, sCode);


        refreshTokenRepository.save(redis);
    }

    // TokenService에서 해당 토큰 무효화 로직
    public void invalidateToken(String token) {
        redisTemplate.opsForSet().add("blacklisted_tokens", token);
    }

    private boolean isPasswordValid(Store store, String password) {
        String passwordEncode = store.getSNone();

        if (passwordEncode == null) {
            return store.getSPw().equals(password);
        } else if (!passwordEncode.isEmpty()) {
            return passwordEncoder.matches(password, store.getSPw());
        } else {
            return false;
        }
    }

    public boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember("blacklisted_tokens", token));
    }

    public String createAccessTokenFromRefreshToken(String refreshToken) {
        if (!jwtProvider.verify(refreshToken)) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰");
        }

        Map<String, Object> claims = jwtProvider.getClaims(refreshToken);
        String role = (String) claims.get("role");
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("role", role);

        switch (role) {
            case "ROLE_USER":
                String email = (String) claims.get("email");
                tokenData.put("email", email);
                break;
            case "ROLE_ADMIN":
                String id = (String) claims.get("id");
                tokenData.put("id", id);
                break;
            case "ROLE_OWNER":
                String sCode = (String) claims.get("sCode");
                tokenData.put("sCode", sCode);
                break;
        }

        return jwtProvider.getAccessToken(tokenData);
    }
}
