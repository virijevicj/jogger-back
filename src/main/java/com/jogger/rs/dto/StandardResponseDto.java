package com.jogger.rs.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class StandardResponseDto {

    @Builder.Default
    private final LocalDateTime time = LocalDateTime.now();
    private Integer statusCode;
    private String message;
    private Object data;

}
