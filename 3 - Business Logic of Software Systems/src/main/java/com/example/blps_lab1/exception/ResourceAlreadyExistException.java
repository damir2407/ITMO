package com.example.blps_lab1.exception;

public class ResourceAlreadyExistException  extends RuntimeException{
    public ResourceAlreadyExistException(String message) {
        super(message);
    }
}
