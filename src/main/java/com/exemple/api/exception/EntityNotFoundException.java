package com.exemple.api.exception;

public class EntityNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Entity not found";
    }
}
