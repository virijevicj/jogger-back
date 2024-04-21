package com.jogger.rs.auth;

import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.user.UserServiceInterface;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Kontroler koji prihvata zahteve vezane za autentifikaciju korisnika, odnosno login funkcionalnost sistema
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.AUTH)
@RequiredArgsConstructor
public class AuthController {

    /**
     * Servis koji se poziva da izvrsi login
     */
    private final UserServiceInterface userService;

    /**
     * Metoda koja prihvata korisnicki zahtev da se izvrsi login
     *
     * @param loginRequestDto objekat koji sadrzi username i password potreban za login
     * @return StandardResponseDto
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto responseDto = userService
                .login(loginRequestDto)
                .orElseThrow(RuntimeException::new);
        return ResponseFactory.ok(SuccessMessage.LOGIN_SUCCESS, responseDto);
    }

}
