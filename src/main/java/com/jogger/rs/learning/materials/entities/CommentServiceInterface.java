package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.dto.NewCommentDto;

public interface CommentServiceInterface {

    void save(NewCommentDto commentDto, String token);
}
