package com.jogger.rs.ml.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_area", nullable = false, unique = true, updatable = false)
    private Integer keyarea;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private AreaName name;

    @JsonIgnore
    @Column(name = "description")
    private String description;
}
