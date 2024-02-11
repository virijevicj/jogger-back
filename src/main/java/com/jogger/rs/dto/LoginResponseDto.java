package com.jogger.rs.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi token i korisnicke uloge u sistemu i salje se nakon uspesnog login-a korisnika na sistem.
 *
 * @author Jovan Virijevic
 */
@Data
@Builder
public class LoginResponseDto {

    /**
     * Jedinistveni token koji se dodeljuje korisniku nakon login-a.
     */
    private String token;

    /**
     * Lista uloga koje korisnik ima u sistemu.
     */
    private List<String> roles;
}
