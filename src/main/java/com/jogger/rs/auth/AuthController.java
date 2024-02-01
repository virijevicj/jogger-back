package com.jogger.rs.auth;

import com.jogger.rs.dto.LoginRequestDto;
import com.jogger.rs.dto.LoginResponseDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.user.UserServiceInterface;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.Optional;

@RestController
@RequestMapping(RequestMappingPrefix.AUTH)
public class AuthController {

    private UserServiceInterface userService;
    private ResponseFactory responseFactory;
    private AuthManager authManager;

    @Autowired
    public AuthController(UserServiceInterface userService, ResponseFactory responseFactory, AuthManager authManager) {
        this.userService = userService;
        this.responseFactory = responseFactory;
        this.authManager = authManager;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> login(HttpServletRequest request, @RequestBody LoginRequestDto loginRequestDto) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        LoginResponseDto responseDto = userService.login(loginRequestDto)
                .orElseThrow(RuntimeException::new);
        return responseFactory.ok(SuccessMessage.LOGIN_SUCCESS, responseDto);
    }

}
