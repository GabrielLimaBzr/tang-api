package com.solides.tangerino.blog.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler({NegocioException.class})
    public ResponseEntity<Object> handleNegocioException(NegocioException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }
}
