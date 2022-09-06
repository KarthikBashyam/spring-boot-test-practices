package com.customer.shows.favourite.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handle(CustomerNotFoundException customerNotFoundException) {
        return new ResponseEntity<String>("Customer Not Found: " + customerNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException exception) {
        exception.printStackTrace();
        return new ResponseEntity<String>("Server Error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
