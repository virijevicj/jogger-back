package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.learning.materials.LearningMaterial;
import com.jogger.rs.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_comment", nullable = false, unique = true, updatable = false)
    private Integer keyComment;

    @Column(name = "text", nullable = true)
    private String text;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_learning_material", referencedColumnName = "key_learning_material")
    private LearningMaterial lm;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "key_user", referencedColumnName = "key_user")
    private User user;
}
