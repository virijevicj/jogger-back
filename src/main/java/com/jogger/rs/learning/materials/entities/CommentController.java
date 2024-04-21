package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.dto.NewCommentDto;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler koji je zaduzen za rad sa komentarima, odnosno dodavanje novih komentara.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.LEARNING_MATERIAL_COMMENT)
@RequiredArgsConstructor
public class CommentController {

    /**
     * Servis koji je zaduzen za rad sa komentarima.
     */
    private final CommentServiceInterface commentService;

    /**
     * Metoda koja je zaduzena za dodavanje novog komentara.
     *
     * @param commentDto novi komentar.
     * @return StandardResponseDto
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> saveComment(@Valid @RequestBody NewCommentDto commentDto) {
        commentService.save(commentDto);
        return ResponseFactory.ok(SuccessMessage.COMMENT_SAVE_SUCCESS);
    }
}
