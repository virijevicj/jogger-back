package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.dto.NewCommentDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.learning.materials.LearningMaterial;
import com.jogger.rs.learning.materials.LearningMaterialServiceInterface;
import com.jogger.rs.user.User;
import com.jogger.rs.user.UserRepository;
import com.jogger.rs.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

/**
 * Implenetacija servisa koji je zaduzen za dodavanje novih komentara.
 *
 * @author Jovan Virijevic
 */
@Service
@RequiredArgsConstructor
public class CommentService implements CommentServiceInterface {

    /**
     * Repozitorijum za komentare.
     */
    private final CommentRepository commentRepository;

    /**
     * Validator.
     */
    private final Validator validator;

    /**
     * Servis za materijale za ucenje.
     */
    private final LearningMaterialServiceInterface lmService;

    /**
     * Repozitorijum za rad sa korisnicima.
     */
    private final UserRepository userRepository;

    @Override
    public void save(NewCommentDto commentDto) {
        String errorMessage = validator.validateNewComment(commentDto);
        if (StringUtils.hasText(errorMessage))
            throw new IllegalArgumentException(errorMessage);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsernameAndActiveTrue(username).orElse(null);

        LearningMaterial lm = lmService.findById(commentDto.getKeyLearningMaterial()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_LEARNING_MATERIAL_FOUND + commentDto.getKeyLearningMaterial())
        );

        Comment newComment = new Comment();
        newComment.setText(commentDto.getText());
        newComment.setGrade(commentDto.getGrade());
        newComment.setUser(user);
        newComment.setLm(lm);
        commentRepository.save(newComment);
    }
}
