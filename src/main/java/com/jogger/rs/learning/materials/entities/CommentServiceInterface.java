package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.dto.NewCommentDto;

/**
 * Serivis koji je zaduzen za dodavanje novih komentara.
 *
 * @author Jovan Virijevic
 */
public interface CommentServiceInterface {
    /**
     * Motoda koja je zaduzena za dodavanje novih komentara.
     *
     * @param commentDto novi komentar.
     * @param token jedinstveni identifikator korisnika.
     */
    void save(NewCommentDto commentDto, String token);
}
