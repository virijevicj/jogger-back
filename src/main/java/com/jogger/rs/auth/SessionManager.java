package com.jogger.rs.auth;

import com.jogger.rs.user.User;
import com.jogger.rs.user.UserSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Optional;

@Component
public final class SessionManager {

    private HashMap<String, UserSession> sessionMap = new HashMap<>();

    // dodavanje sesije usera sa odgovarajucim radnikom
    public boolean createUserSession(String token, UserSession user) {
        if (tokenExists(token) || ObjectUtils.isEmpty(user)) return false;
        sessionMap.put(token, user);
        return true;
    }

    // uklanjanje sesije usera sa odgovarajucim radnik
    public void removeUserSession(String token) {
        sessionMap.remove(token);
    }
    // provera da li odredjena sesija postoji
    public boolean tokenExists(String token) {
        return sessionMap.containsKey(token);
    }

    // vracanje radnikovu sesiju na osnovu cookie-ja
    public Optional<UserSession> getUserFromSession(String token) {
        if (tokenExists(token)) {
            return Optional.ofNullable(sessionMap.get(token));
        }
        return Optional.empty();
    }

    // uklanjanje svih sesija
    public void clearAllSessions() {
        sessionMap = new HashMap<>();
    }

    // proverava da li se mapa sa sesijama prazna
    public boolean isEmpty() {
        return sessionMap.isEmpty();
    }

    // ispis radi provera da li radi mehanizam
    public void sessionMapToString() {
        System.out.println(sessionMap.toString());
    }

    // kada obrisemo user-a uklanjamo i sesiju
    public void deleteUserSession(String token) {
        sessionMap.remove(token);
    }

    // kada azuriramo user-a azuriramo i sesiju
    public void updateUserSession(UserSession user, String token) {
        sessionMap.replace(token,user);
    }
}
