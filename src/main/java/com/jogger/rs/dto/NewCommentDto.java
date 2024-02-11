package com.jogger.rs.dto;

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
    private Integer keyLearningMaterial;

    /**
     * Ocena koja se dodeljuje nekom materijalu za ucenje.
     */
    private Integer grade;

    /**
     * Komentar koji se dodeljuje nekom materijalu za ucenje.
     */
    private String text;
}
