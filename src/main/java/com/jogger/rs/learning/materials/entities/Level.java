package com.jogger.rs.learning.materials.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa koja predstavlja nivo.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "level")
public class Level {

    /**
     * Jedinstveni identifikator nivoa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_level", nullable = false, unique = true, updatable = false)
    private Integer keyLevel;

    /**
     * Naziv nivoa.
     */
    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LevelName name;

    /**
     * Opis nivoa.
     */
    @JsonIgnore
    @Column(name = "description")
    private String description;
}
