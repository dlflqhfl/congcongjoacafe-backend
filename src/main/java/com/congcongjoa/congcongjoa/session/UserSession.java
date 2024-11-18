package com.congcongjoa.congcongjoa.session;

import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Component
public class UserSession {
    
    private static final String SESSION_LOGIN_STATUS = "User";

    public void createSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_LOGIN_STATUS, true);
    }

    public boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        Boolean isLoggedIn = (Boolean) session.getAttribute(SESSION_LOGIN_STATUS);
        return isLoggedIn != null && isLoggedIn;
    }

    public void expireSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
    
}
