package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.ContentType;
import com.jogger.rs.learning.materials.entities.ContentTypeName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repozitorijum za rad sa tipovima sadrzaja materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
public interface ContentTypeRepository extends JpaRepository<ContentType, Integer> {

    /**
     * Metoda koja pronalazi oblast materijala za ucenje na osnovu imena.
     * @param name ime tipa sadrzaja
     * @return Optional<ContentType>
     */
    Optional<ContentType> findByName(ContentTypeName name);
}
