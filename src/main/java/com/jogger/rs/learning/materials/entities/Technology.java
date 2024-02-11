package com.jogger.rs.learning.materials.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa koja predstavlja tehnologiju.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "technology")
public class Technology {

    /**
     * Jedinstveni identifikator tehnologije.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_technology", nullable = false, unique = true, updatable = false)
    private Integer keyTechnology;

    /**
     * Naziv tehnologije.
     */
    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TechnologyName name;

    /**
     * Opis tehnologije.
     */
    @JsonIgnore
    @Column(name = "description")
    private String description;

}
