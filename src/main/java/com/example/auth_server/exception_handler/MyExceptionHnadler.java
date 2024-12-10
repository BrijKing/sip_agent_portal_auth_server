package com.example.auth_server.exception_handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.auth_server.Models.ResponseDTO;
import com.example.auth_server.custom_exceptions.UnableToSaveUserException;


@RestControllerAdvice
public class MyExceptionHnadler {


    @ExceptionHandler(UnableToSaveUserException.class)
    public ResponseEntity<String> UnableToSaveUserException(UnableToSaveUserException e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> handleGenericException(Exception e){
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", e.getMessage());
        

        return new ResponseEntity<>(ResponseDTO.builder()
                                            .object(response)
                                            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                                            .message("An error occured !!")
                                            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
