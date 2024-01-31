package com.jogger.rs.utils;

import com.jogger.rs.dto.StandardResponseDto;
import com.jogger.rs.labels.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public final class ResponseFactory {

    public ResponseEntity<Object> createResponse(Integer statusCode,  String message, Object data) {
        return ResponseEntity.status(statusCode)
                .body(StandardResponseDto.builder()
                        .statusCode(statusCode)
                        .message(message)
                        .data(data)
                        .build()
                );
    }

    public ResponseEntity<Object> somethingWentWrong() {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ErrorMessage.SOMETHING_WENT_WRONG);
    }

    public ResponseEntity<Object> somethingWentWrong(String message) {
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message,
                ErrorMessage.SOMETHING_WENT_WRONG);
    }

    public ResponseEntity<Object> ok(Object data) {
        return createResponse(HttpStatus.OK.value(), HttpStatus.OK.toString(), data);
    }
    public ResponseEntity<Object> ok(String message) {
        return createResponse(HttpStatus.OK.value(), message, "");
    }

    public ResponseEntity<Object> ok(String message, Object data) {
        return createResponse(HttpStatus.OK.value(), message, data);
    }

    public ResponseEntity<Object> badRequest(String message) {
        return createResponse(HttpStatus.BAD_REQUEST.value(), message, "");
    }

    public ResponseEntity<Object> notFound(String message) {
        return createResponse(HttpStatus.NOT_FOUND.value(), message, "");
    }

    public ResponseEntity<Object> forbidden(String message) {
        return createResponse(HttpStatus.FORBIDDEN.value(), message, "");
    }
}
