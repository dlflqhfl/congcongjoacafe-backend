package com.congcongjoa.congcongjoa.jwt.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.congcongjoa.congcongjoa.jwt.JwtProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/api/public/")) {
            filterChain.doFilter(request, response); // 필터를 건너뜀.
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authorizationHeader.substring(7);
        try {
            if (jwtProvider.verify(jwt)) {
                Map<String, Object> claims = jwtProvider.getClaims(jwt);

                System.out.println(claims + "claims");

                String role = (String) claims.get("role");
                //System.out.println(role + "role");
                if (role == null || role.isEmpty()) {
                    logger.warn("Invalid role in JWT");
                    filterChain.doFilter(request, response);
                    return;
                }
                String username = jwtProvider.getUsernameByRole(jwt, role);

                System.out.println(username);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(authority));
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);


                    logger.info("Successfully authenticated " + role);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            logger.warn("JWT processing failed: {}");
        }

        filterChain.doFilter(request, response);
    }
}