package com.exemple.api.app.controller.response;

import lombok.Getter;

@Getter
public class InputExceptionResponse {
    private final String field;
    private final String message;

    public InputExceptionResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
