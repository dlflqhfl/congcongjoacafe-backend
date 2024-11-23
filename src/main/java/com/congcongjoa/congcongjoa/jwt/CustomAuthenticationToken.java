package com.congcongjoa.congcongjoa.jwt;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String sName;


    public CustomAuthenticationToken(String sCode, String password, String sName) {
        super(sCode, password, null);
        this.sName = sName;
    }


}