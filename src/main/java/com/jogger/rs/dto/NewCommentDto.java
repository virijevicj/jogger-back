package com.jogger.rs.dto;

import lombok.Data;

@Data
public class NewCommentDto {
    private Integer keyLearningMaterial;
    private Integer grade;
    private String text;
}
