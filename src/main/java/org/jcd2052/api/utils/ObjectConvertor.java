package org.jcd2052.api.utils;

import aquality.selenium.core.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;

public class ObjectConvertor {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectConvertor() {

    }

    public static <T> T convertResponse(Response response, Class<T> classType) {
        T entity = response.as(classType);
        Logger.getInstance().debug(String.format("Converted a response to %s object. Result: %n %s.",
                classType.getSimpleName(), entity));
        return entity;
    }

    public static <T> T getEntityFromFile(File file, Class<T> classType) {
        try {
            return objectMapper.readValue(file, classType);
        } catch (IOException e) {
            String message = String.format("Couldn't read file with path %s and convert to %s. %n %s",
                    file.getAbsolutePath(), classType.getSimpleName(), e.getMessage());
            Logger.getInstance().error(message);
            throw new IllegalStateException(message);
        }
    }
}
