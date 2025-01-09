package org.example.application.user.exception;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String message) {
        super(message);
    }
}
