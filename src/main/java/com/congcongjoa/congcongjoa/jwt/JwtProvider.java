package com.congcongjoa.congcongjoa.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {



    @Getter
    private final SecretKey secretKey;

    @Value("${custom.jwt.secretKey}") String secretKeyCode;

    private static final int ACCESS_TOKEN_VALIDITY = 3600;      // 1시간
    private static final int REFRESH_TOKEN_VALIDITY = 60 * 60 * 24 * 14; // 14일

    public JwtProvider(@Value("${custom.jwt.secretKey}") String secretKeyCode) {
     this.secretKey = new SecretKeySpec(secretKeyCode.getBytes(StandardCharsets.UTF_8),
             Jwts
                     .SIG
                     .HS256
                     .key()
                     .build()
                     .getAlgorithm());
    }

    private String genToken(Map<String, Object> map, int seconds){

        long now = new Date().getTime();

        Date accessTokenExpiresIn = new Date(now + 1000L * seconds);

        JwtBuilder jwtBuilder = Jwts.builder()
                .subject("role")
                .expiration(accessTokenExpiresIn);

        Set<String> keys = map.keySet();

        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();
            Object value = map.get(key);
            jwtBuilder.claim(key, value);
        }

        return jwtBuilder.signWith(getSecretKey()).compact();
    }



    //엑세스 코드 발급
    public String getAccesToken(Map<String, Object> map){
        return genToken(map, ACCESS_TOKEN_VALIDITY);
    }

    // 리프래시 코드 발급
    public String getRefreshToken(Map<String, Object> map){
        return genToken(map, REFRESH_TOKEN_VALIDITY);
    }

    //오너 전용 이름 뽑아내는 파서
    public String getOwnerName(String token){
        return Jwts.parser().verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("sCode", String.class);
    }

    public String getRole(String token){
        return Jwts.parser().verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    //토큰 유효성 검사
    public boolean verify(String token){
        boolean value = true;
        try {
            Jwts.parser().verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token);
        } catch (Exception e) {
            value = false;
        }
        return value;
    }

    public Map<String, Object> getClaims(String token){
        return Jwts.parser().verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
