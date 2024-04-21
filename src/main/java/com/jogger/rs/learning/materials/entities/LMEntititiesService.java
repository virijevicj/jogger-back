package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.dto.LMEntitiesDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implenetacija servisa koji je zaduzen za pronalazenje entiteta materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
public class LMEntititiesService implements LMEntitiesServiceInterface{

    @Override
    public LMEntitiesDto findAll() {
        return LMEntitiesDto.builder()
                .areas(List.of(AreaName.values()))
                .contentTypes(List.of(ContentTypeName.values()))
                .levels(List.of(LevelName.values()))
                .platforms(List.of(PlatformName.values()))
                .technologies(List.of(TechnologyName.values()))
                .build();
    }
}
