package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Technology;
import com.jogger.rs.learning.materials.entities.TechnologyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozitorijum za rad sa tehnologijama materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

    /**
     * Metoda koja pronalazi tehnologiju materijala za ucenje na osnovu imena.
     * @param name ime tehnologije
     * @return Optional<Technology>
     */
    Optional<Technology> findByName(TechnologyName name);
}
