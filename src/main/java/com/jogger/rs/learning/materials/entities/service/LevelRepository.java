package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Level;
import com.jogger.rs.learning.materials.entities.LevelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozitorijum za rad sa nivoima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {

    /**
     * Metoda koja pronalazi nivoe materijala za ucenje na osnovu imena.
     * @param name ime nivoa
     * @return Optional<Level>
     */
    Optional<Level> findByName(LevelName name);
}
