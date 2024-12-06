package com.example.auth_server.exception_handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.auth_server.custom_exceptions.UnableToSaveUserException;

@RestControllerAdvice
public class MyExceptionHnadler {


    @ExceptionHandler(UnableToSaveUserException.class)
    public ResponseEntity<String> UnableToSaveUserException(UnableToSaveUserException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
