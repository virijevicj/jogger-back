package com.jogger.rs.dto;

import com.jogger.rs.learning.materials.entities.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Dto objekat zaduzen za komunikaciju klijentske i serverske strane aplikacije.
 *
 * Sadrzi sve entitete vezane za materijale za ucenje koji su potrebni za pretragu materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Data
@Builder
public class LMEntitiesDto {

    /**
     * Lista oblasti materijala za ucenje.
     */
    List<AreaName> areas;

    /**
     * Lista tipova sadrzaja materijala za ucenje.
     */
    List<ContentTypeName> contentTypes;

    /**
     * Lista nivoa materijala za ucenje.
     */
    List<LevelName> levels;

    /**
     * Lista platformi materijala za ucenje.
     */
    List<PlatformName> platforms;

    /**
     * Lista tehnologija materijala za ucenje.
     */
    List<TechnologyName> technologies;
}
