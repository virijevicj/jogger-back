package com.jogger.rs.dto;

import com.jogger.rs.labels.JakartaValidationErrorMessages;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi jedinstveni identifikator materijala za ucenje, ocenu i komentar na materijal za ucenje.
 * Koristi se kada se dodaje nov komentar za neki materijal za ucenje.
 *
 * @author Jovan Virijevic
 */
@Data
public class NewCommentDto {

    /**
     * Jedinstveni identifikator materijala za ucenje
     */
    @Min(value = 1, message = JakartaValidationErrorMessages.ID_GREATER_THAN_ZERO)
    @NotNull(message = JakartaValidationErrorMessages.ID_NOT_FOUND)
    private Integer keyLearningMaterial;

    /**
     * Ocena koja se dodeljuje nekom materijalu za ucenje.
     */
    @Min(value = 1, message = JakartaValidationErrorMessages.GRADE_MIN_VALUE)
    @Max(value = 10, message = JakartaValidationErrorMessages.GRADE_MAX_VALUE)
    @NotNull(message = JakartaValidationErrorMessages.GRADE_NOT_FOUND)
    private Integer grade;

    /**
     * Komentar koji se dodeljuje nekom materijalu za ucenje.
     */
    @NotBlank(message = JakartaValidationErrorMessages.COMMENT_NOT_FOUND)
    private String text;
}
