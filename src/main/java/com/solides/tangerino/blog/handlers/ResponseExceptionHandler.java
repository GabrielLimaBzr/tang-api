package com.solides.tangerino.blog.handlers;

import com.solides.tangerino.blog.exceptions.BusinessException;
import com.solides.tangerino.blog.exceptions.NotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        return ResponseEntity.badRequest()
                .body(new CustomExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomExceptionResponse> handleNotFoundException(NotFoundException exception) {
        CustomExceptionResponse customException = new CustomExceptionResponse(exception.getMessage(),
                HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customException);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        StringBuilder errorMessage = new StringBuilder("Erro de Validação. Erro(s): ");

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessage.append(String.format("[%s - %s], ", fieldError.getField(), error.getDefaultMessage()));
            } else {
                errorMessage.append(String.format("[%s], ", error.getDefaultMessage()));
            }
        });

        String finalErrorMessage = errorMessage.toString().substring(0, errorMessage.length() - 2);

        CustomExceptionResponse customException = new CustomExceptionResponse(finalErrorMessage,
                HttpStatus.BAD_REQUEST.value());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customException);
    }

}
