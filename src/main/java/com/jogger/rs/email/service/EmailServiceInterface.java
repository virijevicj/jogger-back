package com.jogger.rs.email.service;

/**
 * Serivis koji je zaduzen za slanje email poruka.
 *
 * @author Jovan Virijevic
 */
public interface EmailServiceInterface {

    /**
     * Motoda koja salje email korisniku nakon sto mu je kreiran nalog.
     *
     * @param email email adresa na koju se salje poruka.
     * @param username kreiran username.
     * @param password kreirana lozinka.
     */
    void sendEmailWithUsernameAndPassword(String email, String username, String password);
}
