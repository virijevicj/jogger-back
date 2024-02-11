package com.jogger.rs.utils;

import com.jogger.rs.dto.StandardResponseDto;
import com.jogger.rs.labels.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Servis koji je zaduzen za kreiranje odgovora na korisnicke zahteve.
 *
 * @author Jovan Virijevic
 */
@Component
public final class ResponseFactory {

    /**
     * Glavna metoda koja kreira odgovor na korisnicki zahtev.
     *
     * @param statusCode statusni kod
     * @param message poruka
     * @param data podaci
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> createResponse(Integer statusCode,  String message, Object data) {
        return ResponseEntity.status(statusCode)
                .body(StandardResponseDto.builder()
                        .statusCode(statusCode)
                        .message(message)
                        .data(data)
                        .build()
                );
    }

    /**
     *  Metoda koja kreira odgovor kada nastane neplanirana serverska greska.
     *
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> somethingWentWrong() {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ErrorMessage.SOMETHING_WENT_WRONG);
    }

    /**
     *  Metoda koja kreira odgovor kada nastane neplanirana serverska greska.
     *
     * @param message poruka
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> somethingWentWrong(String message) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message,
                ErrorMessage.SOMETHING_WENT_WRONG);
    }

    /**
     * Metoda koja kreira odgovor kada je uspesno obradjen korisnicki zahtev.
     *
     * @param data podaci
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> ok(Object data) {
        return createResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), data);
    }

    /**
     * Metoda koja kreira odgovor kada je uspesno obradjen korisnicki zahtev.
     *
     * @param message poruka
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> ok(String message) {
        return createResponse(HttpStatus.OK.value(), message, "");
    }

    /**
     * Metoda koja kreira odgovor kada je uspesno obradjen korisnicki zahtev.
     *
     * @param message poruka
     * @param data podaci
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> ok(String message, Object data) {
        return createResponse(HttpStatus.OK.value(), message, data);
    }

    /**
     * Metoda koja kreira odgovor kada korisnicki zahtev nije adekvatan.
     *
     * @param message poruka
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> badRequest(String message) {
        return createResponse(HttpStatus.BAD_REQUEST.value(), message, "");
    }

    /**
     * Metoda koja kreira odgovor kada nije pronadjen resurs koji korisnik zahteva.
     *
     * @param message poruka
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> notFound(String message) {
        return createResponse(HttpStatus.NOT_FOUND.value(), message, "");
    }

    /**
     * Metoda koja kreira odgovor kada korisnik pokusava da pristupi resursu kojem nema pravo.
     *
     * @param message poruka
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> forbidden(String message) {
        return createResponse(HttpStatus.FORBIDDEN.value(), message, "");
    }

    /**
     * Metoda koja kreira odgovor kada nije poznato koji korisnik je poslao zahtev.
     *
     * @param message poruka
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> unauthorized(String message) {
        return createResponse(HttpStatus.UNAUTHORIZED.value(), message, "");
    }
}
