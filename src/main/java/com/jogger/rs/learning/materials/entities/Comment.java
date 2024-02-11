package com.jogger.rs.learning.materials.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogger.rs.learning.materials.LearningMaterial;
import com.jogger.rs.user.User;
import jakarta.persistence.*;
import lombok.Data;

/**
 * Klasa koja predstavlja komentar za materijal za ucenje.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "comment")
public class Comment {
    /**
     * Jedinstveni identifikator komentara.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_comment", nullable = false, unique = true, updatable = false)
    private Integer keyComment;
    /**
     * Tekst komentara.
     */
    @Column(name = "text", nullable = true)
    private String text;
    /**
     * Ocena materijala za ucenje.
     */
    @Column(name = "grade", nullable = false)
    private Integer grade;
    /**
     * Naziv materijala za ucenje.
     */
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_learning_material", referencedColumnName = "key_learning_material")
    private LearningMaterial lm;
    /**
     * Naziv korisnika.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_user", referencedColumnName = "key_user")
    private User user;
}
