package com.jogger.rs.ml.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "content_type")
public class ContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_content_type", nullable = false, unique = true, updatable = false)
    private Integer keyContentType;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ContentTypeName name;

    @JsonIgnore
    @Column(name = "description")
    private String description;
}
