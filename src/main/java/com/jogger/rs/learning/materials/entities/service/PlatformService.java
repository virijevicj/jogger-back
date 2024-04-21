package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Platform;
import com.jogger.rs.learning.materials.entities.PlatformName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa platformama materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
@RequiredArgsConstructor
public class PlatformService {

    /**
     * Repozitorijum za rad sa platformama materijala za ucenje.
     */
    private final PlatformRepository repository;

    /**
     * Metoda koja pronalazi platforme materijala za ucenje na osnovu imena.
     * @param name ime platforme
     * @return platforma materijala za ucenje
     */
    public Optional<Platform> findByName(PlatformName name) {
        return repository.findByName(name);
    }
}
