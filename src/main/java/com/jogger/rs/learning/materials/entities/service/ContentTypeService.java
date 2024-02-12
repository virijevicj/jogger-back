package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.ContentType;
import com.jogger.rs.learning.materials.entities.ContentTypeName;
import com.jogger.rs.learning.materials.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Servis za rad sa tipovima sadrzaja materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
public class ContentTypeService {

    /**
     * Repozitorijum za rad sa tipovima sadrzaja materijala za ucenje.
     */
    private ContentTypeRepository repository;

    /**
     * Javni konstruktor.
     *
     * @param repository repozitorijum za rad sa tipovima sadrzaja materijala za ucenje
     */
    @Autowired
    public ContentTypeService(ContentTypeRepository repository) {
        this.repository = repository;
    }

    /**
     * Metoda koja pronalazi tip sadrzaja materijala za ucenje na osnovu imena.
     * @param name ime tipa sadrzaja
     * @return tip sadrzaja materijala za ucenje
     */
    public Optional<ContentType> findByName(ContentTypeName name) {
        return repository.findByName(name);
    }
}
