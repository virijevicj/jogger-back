package com.jogger.rs.auth;

import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.user.UserServiceInterface;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

/**
 * Kontroler koji prihvata zahteve vezane za autentifikaciju korisnika, odnosno login i logout funkcionalnosti sistema.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.AUTH)
public class AuthController {

    /**
     * Servis koji se poziva da izvrsi login i logout
     */
    private UserServiceInterface userService;

    /**
     * Servis koji se poziva da kreira odgovor na korisnicki zahtev
     */
    private ResponseFactory responseFactory;

    /**
     * Servis koji se poziva da izvrsi autorizaciju korisnickog zahteva
     */
    private AuthManager authManager;

    /**
     * Javni konstruktor
     *
     * @param userService servis koji se poziva da izvrsi login i logout
     * @param responseFactory servis koji se poziva da kreira odgovor na korisnicki zahtev
     * @param authManager servis koji se poziva da izvrsi autorizaciju korisnickog zahteva
     */
    @Autowired
    public AuthController(UserServiceInterface userService, ResponseFactory responseFactory, AuthManager authManager) {
        this.userService = userService;
        this.responseFactory = responseFactory;
        this.authManager = authManager;
    }

    /**
     * Metoda koja prihvata korisnicki zahtev da se izvrsi login
     *
     * @param request zahtev koji salje klijentska strana
     * @param loginRequestDto objekat koji sadrzi username i password potreban za login
     * @return ResponseObject koji se puni podacima u zavisnosti da li je uspesno izvrsen login ili ne
     * @throws AuthenticationException ako korisnik nema pravo da pristupi datoj putanji
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> login(HttpServletRequest request, @RequestBody LoginRequestDto loginRequestDto) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        LoginResponseDto responseDto = userService.login(loginRequestDto)
                .orElseThrow(RuntimeException::new);
        return responseFactory.ok(SuccessMessage.LOGIN_SUCCESS, responseDto);
    }

    /**
     * Metoda koja prihvata korisnicki zahtev da se izvrsi logout
     *
     * @param request zahtev koji salje klijentska strana
     * @return ResponseObject koji se puni podacima u zavisnosti da li je uspesno izvrsen logout ili ne
     * @throws AuthenticationException ako korisnik nema pravo da pristupi datoj putanji
     */
    @PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> logout(HttpServletRequest request) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        userService.logout(request.getHeader("Token"));
        return responseFactory.ok(SuccessMessage.LOGOUT_SUCCESS);
    }

}
