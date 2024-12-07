package com.congcongjoa.congcongjoa.jwt;

import com.congcongjoa.congcongjoa.service.custom.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String sCode = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        String sName = ((CustomAuthenticationToken) authentication).getSName();

        UserDetails user = tokenService.findUserBySNameAndPasswordAndSCode(sName, password, sCode);

        // null 체크 추가
        if (user == null) {
            throw new BadCredentialsException("Invalid user credentials");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}