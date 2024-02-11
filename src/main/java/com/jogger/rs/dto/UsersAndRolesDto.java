package com.jogger.rs.dto;

import com.jogger.rs.role.Role;
import com.jogger.rs.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi listu korisnika sistema kao i uloga koje postoje u sistemu.
 * Koristi se kada se pronalaze svi korisnici sistema.
 *
 * @author Jovan Virijevic
 */
@Data
@Builder
public class UsersAndRolesDto {

    /**
     * Lista korisnika sistema.
     */
    List<User> users;

    /**
     * Lista uloga koje postoje u sistemu.
     */
    List<Role> roles;
}
