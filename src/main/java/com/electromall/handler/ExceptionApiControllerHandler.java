package com.electromall.handler;

import com.electromall.exception.ErrorResponse;
import com.electromall.exception.ValidCustomException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionApiControllerHandler {

    @ExceptionHandler(ValidCustomException.class)
    public ResponseEntity<ErrorResponse> runtimeExceptionHandler(ValidCustomException exception) {
        List<ErrorResponse.FieldError> errors = new ArrayList<>();
        errors.add(ErrorResponse.FieldError.builder().build());

        return ResponseEntity.badRequest()
                .body(buildFieldErrors(exception.getErrors()[0].getDefaultMessage(), errors));
    }

    private ErrorResponse buildFieldErrors(String message, List<ErrorResponse.FieldError> errors) {
        return ErrorResponse.builder()
                .message(message)
                .errors(errors)
                .build();
    }
}
