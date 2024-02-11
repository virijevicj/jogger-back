package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Technology;
import com.jogger.rs.learning.materials.entities.TechnologyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa tehnologijama materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
public class TechnologyService {

    /**
     * Repozitorijum za rad sa tehnologijama materijala za ucenje.
     */
    private TechnologyRepository repository;

    /**
     * Javni konstruktor.
     *
     * @param repository repozitorijum za rad sa tehnologijama materijala za ucenje
     */
    @Autowired
    public TechnologyService(TechnologyRepository repository) {
        this.repository = repository;
    }

    /**
     * Metoda koja pronalazi tip sadrzaja materijala za ucenje na osnovu imena.
     * @param name ime tehnologije
     * @return Optional<Technology>
     */
    public Optional<Technology> findByName(TechnologyName name) {
        return repository.findByName(name);
    }
}
