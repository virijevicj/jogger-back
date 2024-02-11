package com.jogger.rs.learning.materials;

import com.jogger.rs.learning.materials.entities.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Klasa koja predstavlja materijal za ucenje.
 *
 * @author Jovan Virijevic
 */
@Data
@Entity
@Table(name = "learning_material")
public class LearningMaterial {
    /**
     * Jedinstveni identifikator materijala za ucenje.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_learning_material", nullable = false, unique = true, updatable = false)
    private Integer keyLearningMaterial;
    /**
     * Opis materijala za ucenje.
     */
    @Column(name = "description", nullable = false)
    private String description;
    /**
     * Link materijala za ucenje.
     */
    @Column(name = "link", nullable = false)
    private String link;
    /**
     * Da li je materijal za ucenje aktivan ili ne.
     */
    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private Boolean active;
    /**
     * Oblast materijala za ucenje.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_area", referencedColumnName = "key_area")
    private Area area;
    /**
     * Tip sadrzaja materijala za ucenje.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_content_type", referencedColumnName = "key_content_type")
    private ContentType contentType;
    /**
     * Nivo materijala za ucenje.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_level", referencedColumnName = "key_level")
    private Level level;
    /**
     * Platforma materijala za ucenje.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_platform", referencedColumnName = "key_platform")
    private Platform platform;
    /**
     * Tehnologija materijala za ucenje.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_technology", referencedColumnName = "key_technology")
    private Technology technology;
    /**
     * Komentar materijala za ucenje.
     */
    @OneToMany(mappedBy="lm")
    private List<Comment> comments;
}
