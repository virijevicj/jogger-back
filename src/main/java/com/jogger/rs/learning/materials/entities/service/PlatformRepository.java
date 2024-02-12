package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Platform;
import com.jogger.rs.learning.materials.entities.PlatformName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozitorijum za rad sa platformama materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    /**
     * Metoda koja pronalazi platforme materijala za ucenje na osnovu imena.
     * @param name ime platforme
     * @return platforma materijala za ucenje
     */
    Optional<Platform> findByName(PlatformName name);
}
