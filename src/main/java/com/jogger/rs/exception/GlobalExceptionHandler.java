package com.jogger.rs.exception;

import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.utils.ResponseFactory;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    /* u slucaju da se nesto desi tokom izvrsavanja sto nije pokrivao drugim exceptionhandler-ima
    ali cemo tek otkomentarisati na kraju da bi pratili stack trace tokom razvoja
    @ExceptionHandler(value = {RuntimeException.class})
    public @ResponseBody ResponseEntity<Object> handleRunTimeException(RuntimeException exception) {
        exception.printStackTrace();
        log.error(exception.getMessage());
        return  responseFactory.somethingWentWrong(exception.getMessage());
    }
    */
}
