package com.jogger.rs.learning.materials.entities;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repozitorijum komentara zaduzen za komunikaciju sa bazom.
 *
 * @author Jovan Virijevic
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
