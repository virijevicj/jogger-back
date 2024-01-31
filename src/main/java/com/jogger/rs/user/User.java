package com.jogger.rs.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jogger.rs.role.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "user", indexes = {
        @Index(name = "i_user_username", columnList = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_user", nullable = false, unique = true, updatable = false)
    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Integer keyUser;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    @JsonIgnore
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "key_user"),
            inverseJoinColumns = @JoinColumn(name = "key_role"))
    List<Role> roles;

}
