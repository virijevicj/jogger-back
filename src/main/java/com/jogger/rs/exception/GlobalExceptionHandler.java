package com.jogger.rs.exception;

import com.jogger.rs.utils.ResponseFactory;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;

@CommonsLog
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseFactory responseFactory;

    @Autowired
    public GlobalExceptionHandler(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public @ResponseBody ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        return responseFactory.badRequest(exception.getMessage());
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public @ResponseBody ResponseEntity<Object>  handleNoSuchElementException(NoSuchElementException exception) {
        return responseFactory.notFound(exception.getMessage());
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public @ResponseBody ResponseEntity<Object>  handleAuthenticationException(AuthenticationException exception) {
        // NO_TOKEN_FOUND - > UNAUTHORIZED
        return responseFactory.unauthorized(exception.getMessage());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public @ResponseBody ResponseEntity<Object> handleRunTimeException(RuntimeException exception) {
        log.error(exception.getMessage());
        return responseFactory.somethingWentWrong(exception.getMessage());
    }
}
