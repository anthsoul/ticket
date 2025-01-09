package org.example.infrastructure.utils;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class StringUtils {
    public boolean checkEmptyOrNullString(String s) {
        return  Objects.equals(s, "") || s.isBlank();
    }
}
