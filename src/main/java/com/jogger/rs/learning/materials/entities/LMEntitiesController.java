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

@RestController
@RequestMapping(RequestMappingPrefix.LEARNING_MATERIAL_ENTITIES)
public class LMEntitiesController {

    private LMEntitiesServiceInterface service;
    private AuthManager authManager;
    private ResponseFactory responseFactory;

    @Autowired
    public LMEntitiesController( LMEntitiesServiceInterface service,AuthManager authManager, ResponseFactory responseFactory) {
        this.service = service;
        this.authManager = authManager;
        this.responseFactory = responseFactory;
    }

    @GetMapping(name = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> findAllEntities(HttpServletRequest request) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(service.findAll());
    }
}
