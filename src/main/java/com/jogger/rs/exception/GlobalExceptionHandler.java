package com.jogger.rs.exception;

import com.jogger.rs.utils.ResponseFactory;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;

/**
 * Klasa koja je zaduzena za hvatanje izuzetaka nastalih u aplikaciji i kreiranje odgovora na osnovu tih izuzetaka.
 *
 * @author Jovan Virijevic
 */
@CommonsLog
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Servis koja je zaduzen za kreiranje odgovora korisniku.
     */
    private ResponseFactory responseFactory;

    /**
     * Javni konstruktor.
     *
     * @param responseFactory servis koja je zaduzen za kreiranje odgovora korisniku.
     */
    @Autowired
    public GlobalExceptionHandler(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    /**
     * Metoda koja hvata izuzetak IllegalArgumentException i vraca odgovarajuci response.
     * @param exception uhvacen izuzetak.
     * @return StandardResponseDto
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public @ResponseBody ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        return responseFactory.badRequest(exception.getMessage());
    }

    /**
     * Metoda koja hvata izuzetak NoSuchElementException i vraca odgovarajuci response.
     * @param exception uhvacen izuzetak.
     * @return StandardResponseDto
     */
    @ExceptionHandler(value = {NoSuchElementException.class})
    public @ResponseBody ResponseEntity<Object>  handleNoSuchElementException(NoSuchElementException exception) {
        return responseFactory.notFound(exception.getMessage());
    }

    /**
     * Metoda koja hvata izuzetak AuthenticationException i vraca odgovarajuci response.
     * @param exception uhvacen izuzetak.
     * @return StandardResponseDto
     */
    @ExceptionHandler(value = {AuthenticationException.class})
    public @ResponseBody ResponseEntity<Object>  handleAuthenticationException(AuthenticationException exception) {
        // NO_TOKEN_FOUND - > UNAUTHORIZED
        return responseFactory.unauthorized(exception.getMessage());
    }

    /**
     * Metoda koja hvata izuzetak RuntimeException i vraca odgovarajuci response.
     * @param exception uhvacen izuzetak.
     * @return StandardResponseDto
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public @ResponseBody ResponseEntity<Object> handleRunTimeException(RuntimeException exception) {
        log.error(exception.getMessage());
        return responseFactory.somethingWentWrong(exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.append(fieldError.getDefaultMessage()).append(" ");
        });
        return responseFactory.badRequest(errors.toString());
    }
}
