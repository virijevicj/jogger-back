package com.jogger.rs.dto;

import com.jogger.rs.labels.JakartaValidationErrorMessages;
import com.jogger.rs.learning.materials.entities.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi opis i link novog materijala za ucenje, oblast, nivo, platformu, tehnologiju i tip sadrzaja novog materijala za ucenje.
 * Koristi se kada se dodaje nov materijal za ucenje.
 *
 * @author Jovan Virijevic
 */
@Data
public class NewLMDto {

    /**
     * Opis materijala za ucenje.
     */
    @NotBlank(message = JakartaValidationErrorMessages.DESCRIPTION_NOT_FOUND)
    private String description;

    /**
     * Link do materijala za ucenje.
     */
    @NotBlank(message = JakartaValidationErrorMessages.LINK_NOT_FOUND)
    private String link;

    /**
     * Oblast materijala za ucenje.
     */
    @NotNull(message = JakartaValidationErrorMessages.AREA_NOT_FOUND)
    private AreaName area;

    /**
     * Tip sadrzaja materijala za ucenje.
     */
    @NotNull(message = JakartaValidationErrorMessages.CONTENT_TYPE_NOT_FOUND)
    private ContentTypeName contentType;

    /**
     * Nivo materijala za ucenje.
     */
    @NotNull(message = JakartaValidationErrorMessages.LEVEL_NOT_FOUND)
    private LevelName level;

    /**
     * Platforma na kojoj se nalazi materijal za ucenje.
     */
    @NotNull(message = JakartaValidationErrorMessages.PLATFORM_NOT_FOUND)
    private PlatformName platform;

    /**
     * Tehnologija materijala za ucenje.
     */
    @NotNull(message = JakartaValidationErrorMessages.TECHNOLOGY_NOT_FOUND)
    private TechnologyName technology;
}
