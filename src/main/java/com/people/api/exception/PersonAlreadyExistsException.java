package com.people.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PersonAlreadyExistsException extends RuntimeException {
    public PersonAlreadyExistsException() {
        super("Person already exists");
    }
}
