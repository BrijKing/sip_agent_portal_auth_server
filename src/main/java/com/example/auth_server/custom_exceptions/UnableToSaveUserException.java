package com.example.auth_server.custom_exceptions;

public class UnableToSaveUserException extends Exception {

    public UnableToSaveUserException(String message){
        super(message);
    }
    
}
