package org.example.domain.exception;

public class InvalidArgumentException extends DomainException{
    public InvalidArgumentException(String message) {
        super(message, "INVALID_ARGUMENT");
    }
}
