package com.jogger.rs.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi vreme kad je odgovor kreiran, statusni kod, poruku i podatke koje je sistem generisao za neki korisnicki zahtev.
 * Koristi se uvek kao objekat koji se salje na korisnicki zahtev.
 *
 * @author Jovan Virijevic
 */

@Data
@Builder
public class StandardResponseDto {

    /**
     * Vreme kreiranja odgovora za dati korisnicki zahtev.
     */
    @Builder.Default
    private final LocalDateTime time = LocalDateTime.now();

    /**
     * Statusni kod korisnickog zahteva.
     */
    private Integer statusCode;

    /**
     * Poruka za dati korisnicki zahtev.
     */
    private String message;

    /**
     * Podaci za dati korisnicki zahtev.
     */
    private Object data;

}
