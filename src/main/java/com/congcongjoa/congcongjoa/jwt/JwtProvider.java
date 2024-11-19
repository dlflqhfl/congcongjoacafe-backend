package com.congcongjoa.congcongjoa.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    
    @Value("${custom.jwt.secretKey}")
    private String secretKeyCode;

    private SecretKey secretKey;

    public SecretKey getSecretKey(){
        if( secretKey == null){
            String encoding = Base64.getEncoder().encodeToString(secretKeyCode.getBytes());
		    secretKey = Keys.hmacShaKeyFor(encoding.getBytes());
        }
        return secretKey;
    }

    private String genToken(Map<String, Object> map, int seconds){

        long now = new Date().getTime();

        Date accessTokenExpiresIn = new Date(now + 1000L * seconds);
        
        JwtBuilder jwtBuilder = Jwts.builder()
                                    .subject("user")
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

    public String getAccesToken(Map<String, Object> map){
        return genToken(map, 60*60); 
    }
    
    public String getRefreshToken(Map<String, Object> map){
        return genToken(map, 60*60*24 *100); 
    }

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
