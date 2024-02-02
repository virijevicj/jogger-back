package com.jogger.rs.ml.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "platform")
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_platform", nullable = false, unique = true, updatable = false)
    private Integer keyPlatform;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private PlatformName name;

    @JsonIgnore
    @Column(name = "description")
    private String description;
}
