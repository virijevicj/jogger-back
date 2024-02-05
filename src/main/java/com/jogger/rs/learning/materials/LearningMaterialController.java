package com.jogger.rs.learning.materials;

import com.jogger.rs.auth.AuthManager;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.learning.materials.entities.*;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> findAll(HttpServletRequest request,
                                           @RequestParam(name = "area", required = false) AreaName area,
                                           @RequestParam(name = "contentType", required = false) ContentTypeName contentType,
                                           @RequestParam(name = "level", required = false) LevelName level,
                                           @RequestParam(name = "platform", required = false) PlatformName platform,
                                           @RequestParam(name = "technology", required = false) TechnologyName technology) throws AuthenticationException {
       if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(learningMaterialService.findLearningMaterials(area, contentType, level, platform, technology));
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable(name = "id") Integer id) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        learningMaterialService.deleteById(id);
        return null;
    }
}
