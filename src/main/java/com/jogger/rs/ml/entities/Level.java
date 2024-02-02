package com.jogger.rs.ml.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_level", nullable = false, unique = true, updatable = false)
    private Integer keyLevel;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private LevelName name;

    @JsonIgnore
    @Column(name = "description")
    private String description;
}
