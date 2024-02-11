package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Area;
import com.jogger.rs.learning.materials.entities.AreaName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozitorijum za rad sa oblastima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    /**
     * Metoda koja pronalazi oblast materijala za ucenje na osnovu imena.
     * @param name ime oblasti
     * @return Optional<Area>
     */
    Optional<Area> findByName(AreaName name);
}
