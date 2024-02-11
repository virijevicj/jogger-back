package com.jogger.rs.learning.materials.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa koja predstavlja platformu.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "platform")
public class Platform {

    /**
     * Jedinstveni identifikator platforme.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_platform", nullable = false, unique = true, updatable = false)
    private Integer keyPlatform;

    /**
     * Naziv platforme.
     */
    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PlatformName name;

    /**
     * Opis platforme.
     */
    @JsonIgnore
    @Column(name = "description")
    private String description;
}
