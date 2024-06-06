package org.example.models.errors;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private Error error;


    @Getter
    public static class Error {
        private int code;
        private String message;
    }
}
