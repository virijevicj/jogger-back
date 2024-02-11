package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Platform;
import com.jogger.rs.learning.materials.entities.PlatformName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa platformama materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
public class PlatformService {

    /**
     * Repozitorijum za rad sa platformama materijala za ucenje.
     */
    private PlatformRepository repository;

    /**
     * Javni konstruktor.
     *
     * @param repository repozitorijum za rad sa platformama materijala za ucenje.
     */
    @Autowired
    public PlatformService(PlatformRepository repository) {
        this.repository = repository;
    }

    /**
     * Metoda koja pronalazi platforme materijala za ucenje na osnovu imena.
     * @param name ime platforme
     * @return Optional<Platform>
     */
    public Optional<Platform> findByName(PlatformName name) {
        return repository.findByName(name);
    }
}
