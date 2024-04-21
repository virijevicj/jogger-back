package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Area;
import com.jogger.rs.learning.materials.entities.AreaName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa oblastima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
@RequiredArgsConstructor
public class AreaService {

    /**
     * Repozitorijum za rad sa oblastima materijala za ucenje.
     */
    private final AreaRepository repository;

    /**
     * Metoda koja pronalazi oblast materijala za ucenje na osnovu imena.
     * @param name ime oblasti
     * @return oblast materijala za ucenje
     */
    public Optional<Area> findByName(AreaName name) {
        return repository.findByName(name);
    }
}
