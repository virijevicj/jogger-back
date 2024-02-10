package com.jogger.rs.auth;

import com.jogger.rs.user.UserSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Component
public final class SessionManager {

    private HashMap<String, UserSession> sessionMap = new HashMap<>();

    // dodavanje sesije usera sa odgovarajucim radnikom
    public void createUserSession(String token, UserSession user) {
        if (tokenExists(token) || ObjectUtils.isEmpty(user)) return;
        sessionMap.put(token, user);
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
        return Optional.ofNullable(sessionMap.get(token));
    }

    // uklanjanje svih sesija
    public void clearAllSessions() {
        sessionMap = new HashMap<>();
    }

    // kada obrisemo user-a uklanjamo i sesiju
    public void deleteUserSession(Integer id) {
        for (String token : sessionMap.keySet()) {
            if (Objects.equals(sessionMap.get(token).getKeyUser(), id)) {
                sessionMap.remove(token);
                return;
            }
        }
    }

    // kada azuriramo user-a azuriramo i sesiju
    public void updateUserSession(UserSession user, String token) {
        sessionMap.replace(token,user);
    }

    public HashMap<String, UserSession> getSessionMap() {
        return sessionMap;
    }

}
