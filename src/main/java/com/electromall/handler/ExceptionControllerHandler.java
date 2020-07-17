package com.electromall.handler;


import com.electromall.exception.ErrorResponse;
import com.electromall.exception.ValidCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice(annotations = Controller.class)
public class ExceptionControllerHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse runtimeExceptionHandler(ValidCustomException exception) {
        List<ErrorResponse.FieldError> errors = new ArrayList<>();
        errors.add(ErrorResponse.FieldError.builder().build());

        return buildFieldErrors(
                exception.getErrors()[0].getDefaultMessage(),
                errors);
    }

    private ErrorResponse buildFieldErrors(String message, List<ErrorResponse.FieldError> errors) {
        return ErrorResponse.builder()
                .message(message)
                .errors(errors)
                .build();
    }
}

