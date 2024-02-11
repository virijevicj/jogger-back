package com.jogger.rs.learning.materials.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa koja predstavlja tip sadrzaja.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "content_type")
public class ContentType {

    /**
     * Jedinstveni identifikator tipa sadraja.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_content_type", nullable = false, unique = true, updatable = false)
    private Integer keyContentType;

    /**
     * Naziv tipa sadrzaja.
     */
    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ContentTypeName name;

    /**
     * Opis tipa sadrzaja.
     */
    @JsonIgnore
    @Column(name = "description")
    private String description;
}
