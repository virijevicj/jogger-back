package com.jogger.rs.dto;

import lombok.Data;

import java.util.List;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi jedinstveni identifikator korisnika, username i lozinku potrebni za logovanje na sistem, ime, prezime i email korisnika,
 * da li je aktivan ili obrisan i sistema i listu uloga koje korisnik ima u sistemu.
 * Koristi se uvek kao objekat koji se salje na korisnicki zahtev.
 *
 * @author Jovan Virijevic
 */
@Data
public class UserDto {

    /**
     * Jedinstveni identifikator korisnika u sistemu.
     */
    private Integer keyUser;

    /**
     * Username korisnika potreban za login na sistem.
     */
    private String username;

    /**
     * Lozinka korisnika potrebna za login na sistem.
     */
    private String password;

    /**
     * Ime korisnika.
     */
    private String firstName;

    /**
     * Prezime korisnika.
     */
    private String lastName;

    /**
     * Email adresa korisnika.
     */
    private String email;

    /**
     * Vrednost koja oznacava da li je korisnik aktivan ili obrisan iz sistema.
     */
    private Boolean active;

    /**
     * Lista uloga koje korisnik ima u sistemu.
     */
    List<String> roles;

}
