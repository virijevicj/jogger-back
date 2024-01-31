package com.jogger.rs.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_role", nullable = false, unique = true, updatable = false)
    private Integer keyRole;

    @Column(name = "name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    @JsonIgnore
    @Column(name = "description")
    private String description;
}
