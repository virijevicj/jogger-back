package com.jogger.rs.auth;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class TokenFactory {

    private SessionManager sessionManager;

    public TokenFactory(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public String generateToken() {
        String token = UUID.randomUUID().toString();
        if (!sessionManager.tokenExists(token)) return token;
        return generateToken();
    }

}
