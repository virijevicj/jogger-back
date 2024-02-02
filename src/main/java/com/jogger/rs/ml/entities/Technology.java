package com.jogger.rs.ml.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "techology")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_techology", nullable = false, unique = true, updatable = false)
    private Integer keyContentType;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private TechnologyName name;

    @JsonIgnore
    @Column(name = "description")
    private String description;

}
