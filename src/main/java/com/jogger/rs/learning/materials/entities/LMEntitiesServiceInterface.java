package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.dto.LMEntitiesDto;

import java.util.List;

/**
 * Serivis koji je zaduzen za pronalazenje svih entiteta materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
public interface LMEntitiesServiceInterface {
    /**
     * Motoda koja je zaduzena za pronalazenje svih entiteta materijala za ucenje.
     *
     * @return LMEntitiesDto
     */
    LMEntitiesDto findAll();
}
