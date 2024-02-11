package com.jogger.rs.dto;

import lombok.Data;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi username i password potreban za login korisnika na sistem.
 *
 * @author Jovan Virijevic
 */
@Data
public class LoginRequestDto {

    /**
     * Username korisnika potrebna za login.
     */
    private String username;

    /**
     * Lozinka korisnika potrebna za login.
     */
    private String password;

}
