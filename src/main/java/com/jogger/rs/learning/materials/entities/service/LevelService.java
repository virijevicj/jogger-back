package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Level;
import com.jogger.rs.learning.materials.entities.LevelName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa nivoima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
@RequiredArgsConstructor
public class LevelService {

    /**
     * Repozitorijum za rad sa nivoima materijala za ucenje.
     */
    private final LevelRepository repository;

    /**
     * Metoda koja pronalazi nivo materijala za ucenje na osnovu imena.
     * @param name ime nivoa
     * @return nivo materijala za ucenje
     */
    public Optional<Level> findByName(LevelName name) {
        return repository.findByName(name);
    }
}
