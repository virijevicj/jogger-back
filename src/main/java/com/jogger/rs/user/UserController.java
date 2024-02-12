package com.jogger.rs.user;

import com.jogger.rs.auth.AuthManager;
import com.jogger.rs.dto.UserDto;
import com.jogger.rs.dto.UsersAndRolesDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.role.Role;
import com.jogger.rs.role.RoleServiceInterface;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Kontroler koji sluzi za rad sa korisnicima.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.USER)
public class UserController {

    /**
     * Servis zaduzen za autorizaciju korisnickih zahteva.
     */
    private AuthManager authManager;

    /**
     * Servis zaduzen za rad sa korisnicima.
     */
    private UserServiceInterface userService;

    /**
     * Servis zaduzen za kreiranje korisnickih odgovora.
     */
    private ResponseFactory responseFactory;

    /**
     * Servis zaduzen za rad sa ulogama.
     */
    private RoleServiceInterface roleService;

    /**
     * Javni konstruktor.
     *
     * @param authManager servis zaduzen za autorizaciju korisnickih zahteva
     * @param userService servis zaduzen za rad sa korisnicima
     * @param responseFactory servis zaduzen za kreiranje korisnickih odgovora
     * @param roleService servis zaduzen za rad sa ulogama
     */
    public UserController(AuthManager authManager, UserServiceInterface userService, ResponseFactory responseFactory,
                          RoleServiceInterface roleService) {
        this.authManager = authManager;
        this.userService = userService;
        this.responseFactory = responseFactory;
        this.roleService = roleService;
    }

    /**
     * Metoda koja pronalazi sve korisnike u sistemu (aktivne + obrisane)
     *
     * @param request korisnicki zahtev
     * @return StandardResponseDto
     * @throws AuthenticationException ako korisnik nema pravo da pristupi resursu na datoj putanji
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> findAll(HttpServletRequest request) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        return responseFactory.ok(UsersAndRolesDto.builder()
                .users(users)
                .roles(roles)
                .build()) ;
    }

    /**
     * Metoda koja pronalazi jednog korisnika sistema
     *
     * @param request korisnicki zahtev
     * @param id jedinstveni identifikator korisnika
     * @return StandardResponseDto
     * @throws AuthenticationException ako korisnik nema pravo da pristupi resursu na datoj putanji
     */
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> findById(HttpServletRequest request, @PathVariable(name = "id") Integer id) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(userService.findById(id).orElseThrow(() ->
                new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + id))) ;
    }

    /**
     * Metoda koja brise korisnika iz sistema.
     *
     * @param request korisnicki zahtev
     * @param id jedinstveni identifikator korisnika
     * @return StandardResponseDto
     * @throws AuthenticationException ako korisnik nema pravo da pristupi resursu na datoj putanji
     */
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> deleteById(HttpServletRequest request, @PathVariable(name = "id") Integer id) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        userService.deleteById(id);
        return responseFactory.ok(SuccessMessage.USER_DELETE_SUCCESS + id);
    }

    /**
     * Metoda koja dodaje novog korisnika u sistem.
     *
     * @param request korisnicki zahtev
     * @param newUser novi korisnik
     * @return StandardResponseDto
     * @throws AuthenticationException ako korisnik nema pravo da pristupi resursu na datoj putanji
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> save(HttpServletRequest request, @RequestBody UserDto newUser) throws AuthenticationException {
        if (!authManager.auth(request)) {
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        }
        userService.save(newUser);
        return responseFactory.ok(SuccessMessage.USER_SAVE_SUCCESS + newUser.getUsername());
    }

    /**
     * Metoda koja azurira postojeceg korisnika.
     *
     * @param request korisnicki zahtev
     * @param user korisnik
     * @return StandardResponseDto
     * @throws AuthenticationException ako korisnik nema pravo da pristupi resursu na datoj putanji
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> update(HttpServletRequest request, @RequestBody UserDto user) throws AuthenticationException {
        if (!authManager.auth(request)) {
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        }
        String token = request.getHeader("Cookie");
        String message = userService.update(user, token) ? SuccessMessage.USER_UPDATE_SUCCESS : SuccessMessage.USER_UPDATE_SUCCESS_BUT_NOTHING_WAs_DIFFERENT ;
        return responseFactory.ok(message + user.getKeyUser());
    }

}
