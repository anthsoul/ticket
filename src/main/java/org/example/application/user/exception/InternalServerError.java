package org.example.application.user.exception;

public class InternalServerError extends RuntimeException{
    public InternalServerError(String message) {
        super(message);
    }
}
