package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Level;
import com.jogger.rs.learning.materials.entities.LevelName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa nivoima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
public class LevelService {
    /**
     * Repozitorijum za rad sa nivoima materijala za ucenje.
     */
    private LevelRepository repository;

    /**
     * Javni kosntruktor.
     *
     * @param repository repozitorijum za rad sa nivoima materijala za ucenje
     */
    @Autowired
    public LevelService(LevelRepository repository) {
        this.repository = repository;
    }

    /**
     * Metoda koja pronalazi nivo materijala za ucenje na osnovu imena.
     * @param name ime nivoa
     * @return Optional<Level>
     */
    public Optional<Level> findByName(LevelName name) {
        return repository.findByName(name);
    }
}
