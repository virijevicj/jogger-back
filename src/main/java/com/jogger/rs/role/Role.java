package com.jogger.rs.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa koja predstavlja ulogu u sistemu.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    /**
     * Jedinstveni identifikator uloge.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_role", nullable = false, unique = true, updatable = false)
    private Integer keyRole;

    /**
     * Ime uloge.
     */
    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    /**
     * Opis uloge.
     */
    @JsonIgnore
    @Column(name = "description")
    private String description;
}
