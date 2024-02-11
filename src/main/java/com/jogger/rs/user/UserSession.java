package com.jogger.rs.user;

import com.jogger.rs.role.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;;

/**
 * Klasa sa vaznim podacima potrebnim za pracenje sesije korisnika
 *
 * @author Jovan Virijevic
 */
@Getter
@Setter
@ToString
public class UserSession {
    /**
     * Jedinstveni identifikator korisnika.
     */
    private Integer keyUser;

    /**
     * Uloge korisnika u sistemu.
     */
    List<Role> roles;
}
