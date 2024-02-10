package com.jogger.rs.junit;

import com.jogger.rs.auth.SessionManager;
import com.jogger.rs.user.UserSession;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class SessionManagerTest {

    SessionManager sessionManager;

    @BeforeEach
    void setUp() throws Exception {
        sessionManager = new SessionManager();
        String token = "testni-token";
        UserSession userSession = new UserSession();
        sessionManager.getSessionMap().put(token, userSession);
    }

    @AfterEach
    void tearDown() throws Exception {
        sessionManager = null;
    }

    @Test
    @DisplayName("SessionManagerTest - provera postojanja tokena (ne postoji)")
    void tokenExistsFalse() {
        assertFalse(sessionManager.tokenExists(""));
    }

    @Test
    @DisplayName("SessionManagerTest - provera postojanja tokena (postoji)")
    void tokenExistsTrue() {
        assertTrue(sessionManager.tokenExists("testni-token"));
    }

    @Test
    @DisplayName("SessionManagerTest - provera uklanjanja sesije user-a")
    void removeUserSession() {
        sessionManager.removeUserSession("testni-token");
        assertFalse(sessionManager.getSessionMap().containsKey("testni-token"));
    }

    @Test
    @DisplayName("SessionManagerTest - kreiranje sesija user-a ")
    void createUserSessionTokenAlreadyExists() {
        sessionManager.createUserSession("nov-korisnik", new UserSession());
        assertTrue(sessionManager.getSessionMap().containsKey("nov-korisnik"));
    }

    @Test
    @DisplayName("SessionManagerTest - dohvatanje sesija user-a (token ne postoji)")
    void getUserFromSessionTokenDoesNotExists() {
        assertTrue(sessionManager.getUserFromSession("token-koji-ne-postoji").isEmpty());
    }

    @Test
    @DisplayName("SessionManagerTest - dohvatanje sesija user-a (token postoji)")
    void getUserFromSessionTokenDoesExists() {
        assertInstanceOf(UserSession.class, sessionManager.getUserFromSession("testni-token").orElse(null));
    }

    @Test
    @DisplayName("SessionManagerTest - uklanjanja svih sesija")
    void clearAllSessions() {
        sessionManager.clearAllSessions();
        assertTrue(sessionManager.getSessionMap().isEmpty());
    }

}
