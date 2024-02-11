package com.jogger.rs.auth;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Servis zaduzen za generisanje jedinstvenog tokena koji se dodeljuje korisniku nakon login-a
 *
 * @author Jovan Virijevic
 */
@Component
public final class TokenFactory {

    /**
     * Servis koji vodi racuna o korisnickim sesijama
     */
    private SessionManager sessionManager;

    /**
     * Javni konstruktor
     *
     * @param sessionManager servis koji vodi racuna o korisnickim sesijama
     */
    public TokenFactory(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Metoda koje generise jedinstveni token za korisnika nakon login-a
     *
     * @return jedinstveni token korisnika
     */
    public String generateToken() {
        String token = UUID.randomUUID().toString();
        if (!sessionManager.tokenExists(token)) return token;
        return generateToken();
    }

}
