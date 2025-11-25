package com.firmino.aqueles.ErrorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JsonParsingException.class)
    public ResponseEntity<String> tempJsonNotFound(JsonParsingException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }


}
