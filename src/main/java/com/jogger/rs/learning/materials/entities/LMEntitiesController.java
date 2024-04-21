package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kontroler koji je zaduzen za rad sa entitetima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES)
@RequiredArgsConstructor
public class LMEntitiesController {

    /**
     * Servis koji je zaduzen za rad sa enitetima materijala za ucenje.
     */
    private final LMEntitiesServiceInterface service;

    /**
     * Metoda koja je zaduzena za pronalazenje svih entiteta materijala za ucenje.
     *
     * @return StandardResponseDto
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> findAllEntities() {
        return ResponseFactory.ok(service.findAll());
    }
}
