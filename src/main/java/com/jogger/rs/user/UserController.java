package com.jogger.rs.user;

import com.jogger.rs.dto.UserDto;
import com.jogger.rs.dto.UsersAndRolesDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.role.Role;
import com.jogger.rs.role.RoleServiceInterface;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Kontroler koji sluzi za rad sa korisnicima.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.USER)
@RequiredArgsConstructor
public class UserController {

    /**
     * Servis zaduzen za rad sa korisnicima.
     */
    private final UserServiceInterface userService;


    /**
     * Servis zaduzen za rad sa ulogama.
     */
    private final RoleServiceInterface roleService;

    /**
     * Metoda koja pronalazi sve korisnike u sistemu (aktivne + obrisane)
     *
     * @return StandardResponseDto
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> findAll() {
        List<User> users = userService.findAll();
        List<Role> roles = roleService.findAll();
        return ResponseFactory.ok(UsersAndRolesDto.builder()
                .users(users)
                .roles(roles)
                .build()) ;
    }

    /**
     * Metoda koja pronalazi jednog korisnika sistema
     *
     * @param id jedinstveni identifikator korisnika
     * @return StandardResponseDto
     */
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> findById(@PathVariable(name = "id") Integer id) {
        return ResponseFactory.ok(userService.findById(id).orElseThrow(() ->
                new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + id))) ;
    }

    /**
     * Metoda koja brise korisnika iz sistema.
     *
     * @param id jedinstveni identifikator korisnika
     * @return StandardResponseDto
     */
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> deleteById(@PathVariable(name = "id") Integer id) {
        userService.deleteById(id);
        return ResponseFactory.ok(SuccessMessage.USER_DELETE_SUCCESS + id);
    }

    /**
     * Metoda koja dodaje novog korisnika u sistem.
     *
     * @param newUser novi korisnik
     * @return StandardResponseDto
     */
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> save(@RequestBody UserDto newUser) {
        userService.save(newUser);
        return ResponseFactory.ok(SuccessMessage.USER_SAVE_SUCCESS + newUser.getUsername());
    }

    /**
     * Metoda koja azurira postojeceg korisnika.
     *
     * @param user korisnik
     * @return StandardResponseDto
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> update(@Valid @RequestBody UserDto user) {
        String message = userService.update(user) ? SuccessMessage.USER_UPDATE_SUCCESS : SuccessMessage.USER_UPDATE_SUCCESS_BUT_NOTHING_WAs_DIFFERENT;
        return ResponseFactory.ok(message + user.getKeyUser());
    }

}
