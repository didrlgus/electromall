package com.electromall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidCustomException extends RuntimeException{
    private Error[] errors;

    public ValidCustomException(String defaultMessage) {
        this.errors = new Error[] {new Error(defaultMessage)};
    }

    public ValidCustomException(String defaultMessage, String field){
        this.errors = new Error[]{new Error(defaultMessage, field)};
    }

    public Error[] getErrors() {
        return errors;
    }

    public static class Error {

        private String defaultMessage;
        private String field;

        public Error(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }

        public Error(String defaultMessage, String field) {
            this.defaultMessage = defaultMessage;
            this.field = field;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }

        public String getField() {
            return field;
        }
    }
}
