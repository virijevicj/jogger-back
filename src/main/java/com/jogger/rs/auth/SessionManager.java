package com.jogger.rs.auth;

import com.jogger.rs.user.UserSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

/**
 * Klasa koja je zaduzena da upravlja korisnickim sesijama
 *
 * @author Jovan Virijevic
 */
@Component
public final class SessionManager {

    /**
     * Mapa tokena korisnika i potrebnih korisnickih informacija
     */
    private HashMap<String, UserSession> sessionMap = new HashMap<>();

    /**
     * Metoda koja dodaje novu sesiju korisnika
     *
     * @param token jedinstveni token koji se dodeljuje korisniku nakon izvrsenog login-a
     * @param user informacije o korisniku koje se cuvaju u sesiji
     */
    public void createUserSession(String token, UserSession user) {
        if (tokenExists(token) || ObjectUtils.isEmpty(user)) return;
        sessionMap.put(token, user);
    }

    /**
     * Metoda koja uklanja sesiju korinika
     *
     * @param token jedinstveni token koji se dodeljuje korisniku nakon izvrsenog login-a
     */
    public void removeUserSession(String token) {
        sessionMap.remove(token);
    }

    /**
     * Metoda koja proverava da li postoji token u mapi korisnickih sesija
     *
     * @param token jedinstveni token koji se dodeljuje korisniku nakon izvrsenog login-a
     * @return
     * <ul>
     *     <li> true - ako token postoji u mapi korisnickih sesija </li>
     *     <li> false - ako token ne postoji u mapi korisnickih sesija</li>
     * </ul>
     */
    public boolean tokenExists(String token) {
        return sessionMap.containsKey(token);
    }

    /**
     * Metoda koja vraca informacije o korisniku na osnovu njegovog tokena
     *
     * @param token jedinstveni token koji se dodeljuje korisniku nakon izvrsenog login-a
     * @return
     * <ul>
     *     <li> Optional<UserSession> - ako postoji korisnicka sesija za dati token </li>
     *     <li> Optional.empty() - ako ne postoji korisnicka sesija za dati token</li>
     * </ul>
     */
    public Optional<UserSession> getUserFromSession(String token) {
        return Optional.ofNullable(sessionMap.get(token));
    }

    /**
     * Metoda koja uklanja sve korisnicke sesije iz mape korisnickih sesija
     */
    public void clearAllSessions() {
        sessionMap = new HashMap<>();
    }

    /**
     * Metoda koja brise sesiju korisnika nakon sto je korisnik izbrisan iz sistema
     *
     * @param id jedinistveni identifikator korisnika sistema
     */
    public void deleteUserSession(Integer id) {
        for (String token : sessionMap.keySet()) {
            if (Objects.equals(sessionMap.get(token).getKeyUser(), id)) {
                sessionMap.remove(token);
                return;
            }
        }
    }

    /**
     * Metoda koja azurira korisnicku sesiju nakon sto je korisnik azuriran u sistemu
     *
     * @param user korisnik koji je azuriran (nove uloge korisnika)
     * @param token jedinstveni token koji se dodeljuje korisniku nakon izvrsenog login-a
     */
    public void updateUserSession(UserSession user, String token) {
        sessionMap.replace(token,user);
    }

    /**
     * Metoda koja vraca mapu korisnickih sesija
     *
     * @return mapa korisnickih sesija
     */
    public HashMap<String, UserSession> getSessionMap() {
        return sessionMap;
    }

}
