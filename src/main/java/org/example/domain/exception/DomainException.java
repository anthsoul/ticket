package org.example.domain.exception;

import java.time.LocalDateTime;

public class DomainException extends  RuntimeException{
    private String code;
    private LocalDateTime time;
    public DomainException(String message, String code) {
        super(message);
        this.code = code;
        this.time = LocalDateTime.now();
    }
}
