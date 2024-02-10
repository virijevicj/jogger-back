package com.jogger.rs.learning.materials;

import com.jogger.rs.learning.materials.entities.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "learning_material")
public class LearningMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_learning_material", nullable = false, unique = true, updatable = false)
    private Integer keyLearningMaterial;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private Boolean active;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_area", referencedColumnName = "key_area")
    private Area area;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_content_type", referencedColumnName = "key_content_type")
    private ContentType contentType;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_level", referencedColumnName = "key_level")
    private Level level;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_platform", referencedColumnName = "key_platform")
    private Platform platform;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_technology", referencedColumnName = "key_technology")
    private Technology technology;

    @OneToMany(mappedBy="lm")
    private List<Comment> comments;
}
