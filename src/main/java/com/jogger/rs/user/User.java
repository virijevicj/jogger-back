package com.jogger.rs.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jogger.rs.role.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Predstavlja korisnika sistema.
 *
 * Korisnik ima id, username, lozinku, ime, prezime, email da li je aktivan ili ne, kao i listu uloga koje ima u sistemu.
 *
 * @author Jovan Virijevic
 *
 */
@Data
@Entity
@Table(name = "user", indexes = {
        @Index(name = "i_user_username", columnList = "username")
})
public class User {

    /**
     * Jedinstveni identifikator korisnika
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_user", nullable = false, unique = true, updatable = false)
    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Integer keyUser;

    /**
     * Username koji korisnik koristi da se uloguje na sistem
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Lozinka koju korisnik koristi da se uloguje na sistem
     */
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Ime korisnika
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Prezime korisnika
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Email korisnika
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * Da li je korisnik aktivan ili ne (obrisan)
     */
    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private Boolean active;

    /**
     * Lista uloga koje korisnik ima
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "key_user"),
            inverseJoinColumns = @JoinColumn(name = "key_role"))
    List<Role> roles;

}
