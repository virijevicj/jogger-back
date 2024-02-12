package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.auth.AuthManager;
import com.jogger.rs.dto.LMEntitiesDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.learning.materials.LearningMaterialServiceInterface;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;
import java.util.List;

/**
 * Kontroler koji je zaduzen za rad sa entitetima materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES)
public class LMEntitiesController {
    /**
     * Servis koji je zaduzen za rad sa enitetima materijala za ucenje.
     */
    private LMEntitiesServiceInterface service;
    /**
     * Servis koji je zaduzen za autorizaciju korisnickih zahteva.
     */
    private AuthManager authManager;
    /**
     * Servis koji je zaduzen za kreiranje odgovora na korisnicke zahteve.
     */
    private ResponseFactory responseFactory;

    /**
     * Javni konstruktor.
     *
     * @param service servis koji je zaduzen za rad sa enitetima materijala za ucenje.
     * @param authManager servis koji je zaduzen za autorizaciju korisnickih zahteva.
     * @param responseFactory servis koji je zaduzen za kreiranje odgovora na korisnicke zahteve.
     */
    @Autowired
    public LMEntitiesController( LMEntitiesServiceInterface service,AuthManager authManager, ResponseFactory responseFactory) {
        this.service = service;
        this.authManager = authManager;
        this.responseFactory = responseFactory;
    }

    /**
     * Metoda koja je zaduzena za pronalazenje svih entiteta materijala za ucenje.
     * @param request zahtev koji salje klijentska strana.
     * @return StandardResponseDto
     * @throws AuthenticationException ako korisnik nema pravo da pristupi datoj putanji.
     */
    @GetMapping(name = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> findAllEntities(HttpServletRequest request) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(service.findAll());
    }
}
