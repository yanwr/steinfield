package com.my.steinfield.Steinfield.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExceptionBuilder {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<StandardError> notFound(DataNotFoundException ex, HttpServletRequest req) {
        String messageError = "Data not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                messageError,
                ex.getMessage(),
                req.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
    @ExceptionHandler(DataInvalidException.class)
    public ResponseEntity<StandardError> dataInvalid(DataInvalidException ex, HttpServletRequest req) {
        String messageError = "Data invalid";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                messageError,
                ex.getMessage(),
                req.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> dataInvalid(AuthorizationException ex, HttpServletRequest req) {
        String messageError = "Access denied";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError error = new StandardError(
                Instant.now(),
                status.value(),
                messageError,
                ex.getMessage(),
                req.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
}