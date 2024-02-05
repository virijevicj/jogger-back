package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.auth.SessionManager;
import com.jogger.rs.dto.NewCommentDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.learning.materials.LearningMaterial;
import com.jogger.rs.learning.materials.LearningMaterialServiceInterface;
import com.jogger.rs.user.User;
import com.jogger.rs.user.UserServiceInterface;
import com.jogger.rs.user.UserSession;
import com.jogger.rs.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.NoSuchElementException;

@Service
public class CommentService implements CommentServiceInterface{

    private CommentRepository commentRepository;
    private Validator validator;
    private UserServiceInterface userService;
    private SessionManager sessionManager;
    private LearningMaterialServiceInterface lmService;
    @Autowired
    public CommentService(CommentRepository commentRepository, Validator validator, UserServiceInterface userService,
                          SessionManager sessionManager, LearningMaterialServiceInterface lmService) {
        this.commentRepository = commentRepository;
        this.validator = validator;
        this.userService = userService;
        this.sessionManager = sessionManager;
        this.lmService = lmService;
    }

    @Override
    public void save(NewCommentDto commentDto, String token) {
        String errorMessage = validator.validateNewComment(commentDto);
        if (StringUtils.hasText(errorMessage))
            throw new IllegalArgumentException(errorMessage);
        UserSession userSession = sessionManager.getUserFromSession(token).get();
        User user = userService.findById(userSession.getKeyUser()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + userSession.getKeyUser())
        );
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
