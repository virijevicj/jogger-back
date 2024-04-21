package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Technology;
import com.jogger.rs.learning.materials.entities.TechnologyName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa tehnologijama materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
@RequiredArgsConstructor
public class TechnologyService {

    /**
     * Repozitorijum za rad sa tehnologijama materijala za ucenje.
     */
    private final TechnologyRepository repository;

    /**
     * Metoda koja pronalazi tip sadrzaja materijala za ucenje na osnovu imena.
     * @param name ime tehnologije
     * @return tehnologija materijala za ucenje
     */
    public Optional<Technology> findByName(TechnologyName name) {
        return repository.findByName(name);
    }
}
