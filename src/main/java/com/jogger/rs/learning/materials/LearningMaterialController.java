package com.jogger.rs.learning.materials;

import com.jogger.rs.auth.AuthManager;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping(RequestMappingPrefix.LEARNING_MATERIAL)
public class LearningMaterialController {

    private LearningMaterialServiceInterface learningMaterialService;
    private AuthManager authManager;
    private ResponseFactory responseFactory;

    @Autowired
    public LearningMaterialController(LearningMaterialServiceInterface learningMaterialService ,AuthManager authManager, ResponseFactory responseFactory) {
        this.learningMaterialService = learningMaterialService;
        this.authManager = authManager;
        this.responseFactory = responseFactory;
    }

    @GetMapping(name = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> findAll(HttpServletRequest request) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(learningMaterialService.findAll());
    }
}
