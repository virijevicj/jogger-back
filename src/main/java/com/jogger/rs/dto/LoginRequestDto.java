package com.jogger.rs.dto;

import com.jogger.rs.labels.JakartaValidationErrorMessages;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = JakartaValidationErrorMessages.USERNAME_NOT_FOUND)
    private String username;

    /**
     * Lozinka korisnika potrebna za login.
     */
    @NotBlank(message = JakartaValidationErrorMessages.PASSWORD_NOT_FOUND)
    private String password;

}
