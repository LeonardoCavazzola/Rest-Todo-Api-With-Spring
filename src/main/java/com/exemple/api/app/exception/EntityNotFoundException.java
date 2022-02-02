package com.exemple.api.app.exception;

public class EntityNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Entity not found";
    }
}
