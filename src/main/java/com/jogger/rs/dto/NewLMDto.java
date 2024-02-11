package com.jogger.rs.dto;

import com.jogger.rs.learning.materials.entities.*;
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
    private String description;

    /**
     * Link do materijala za ucenje.
     */
    private String link;

    /**
     * Oblast materijala za ucenje.
     */
    private AreaName area;

    /**
     * Tip sadrzaja materijala za ucenje.
     */
    private ContentTypeName contentType;

    /**
     * Nivo materijala za ucenje.
     */
    private LevelName level;

    /**
     * Platforma na kojoj se nalazi materijal za ucenje.
     */
    private PlatformName platform;

    /**
     * Tehnologija materijala za ucenje.
     */
    private TechnologyName technology;
}
