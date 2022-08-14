package com.customer.shows.favourite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException customerNotFoundException) {
        return new ResponseEntity<String>("Customer Not Found:" + customerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
