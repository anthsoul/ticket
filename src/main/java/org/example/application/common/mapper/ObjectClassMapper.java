package org.example.application.common.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.user.exception.InternalServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObjectClassMapper {
    private final Logger logger = LoggerFactory.getLogger(ObjectClassMapper.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T mapToObject(String objectString, Class<T> type) {
        try {
            return objectMapper.readValue(objectString, type);
        } catch (Exception e) {
            logger.error("Error mapping string to object of type {}: {}", type.getName(), e.getMessage(), e);
            throw new InternalServerError("Error converting object");
        }
    }

    public <T> T mapToObject(String objectString, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(objectString, typeReference);
        } catch (Exception e) {
            logger.error("Error mapping string to object of type {}: {}", typeReference.getType(), e.getMessage(), e);
            throw new InternalServerError("Error converting object");
        }
    }

    public String mapToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("Error mapping object to string: {}", object.getClass().getName(), e);
            throw new InternalServerError("Error converting object to JSON string");
        }
    }

    // Map từ List hoặc Collection sang JSON String
    public String mapToString(List<?> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (Exception e) {
            logger.error("Error mapping list to string: {}", e.getMessage(), e);
            throw new InternalServerError("Error converting list to JSON string");
        }
    }
}

