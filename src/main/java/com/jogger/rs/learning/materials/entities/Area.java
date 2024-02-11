package com.jogger.rs.learning.materials.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa koja predstavlja oblast materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "area")
public class Area {
    /**
     * Jedinstveni identifikator oblasti.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_area", nullable = false, unique = true, updatable = false)
    private Integer keyarea;
    /**
     * Naziv oblasti.
     */
    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private AreaName name;
    /**
     * Opis oblasti.
     */
    @JsonIgnore
    @Column(name = "description")
    private String description;
}
