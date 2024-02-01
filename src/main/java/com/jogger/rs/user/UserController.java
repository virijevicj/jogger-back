package com.jogger.rs.user;

import com.jogger.rs.auth.AuthManager;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(RequestMappingPrefix.USER)
public class UserController {

    private AuthManager authManager;
    private UserServiceInterface userService;
    private ResponseFactory responseFactory;

    public UserController(AuthManager authManager, UserServiceInterface userService, ResponseFactory responseFactory) {
        this.authManager = authManager;
        this.userService = userService;
        this.responseFactory = responseFactory;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> findAll(HttpServletRequest request) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(userService.findAll()) ;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> findById(HttpServletRequest request, @PathVariable(name = "id") Integer id) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(userService.findById(id).orElseThrow(() ->
                new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + id))) ;
    }

}
