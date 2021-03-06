package com.uit.coursemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class InvalidException extends RuntimeException{
    public InvalidException(String message) {
        super(message);
    }
}
