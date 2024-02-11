package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Area;
import com.jogger.rs.learning.materials.entities.AreaName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa oblastima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
public class AreaService {

    /**
     * Repozitorijum za rad sa oblastima materijala za ucenje.
     */
    private AreaRepository repository;

    /**
     * Javni konstruktor.
     *
     * @param repository repozitorijum za rad sa oblastima materijala za ucenje
     */
    @Autowired
    public AreaService(AreaRepository repository) {
        this.repository = repository;
    }

    /**
     * Metoda koja pronalazi oblast materijala za ucenje na osnovu imena.
     * @param name ime oblasti
     * @return Optional<Area>
     */
    public Optional<Area> findByName(AreaName name) {
        return repository.findByName(name);
    }
}
