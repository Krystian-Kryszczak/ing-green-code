package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public final class TestUtils {
    private final ObjectMapper objectMapper;

    public TestUtils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T parseFromJson(final String pathToFile, Class<T> clazz) throws IOException {
        final var jsonRequest = Files.readString(Path.of(pathToFile));
        return objectMapper.readValue(jsonRequest, clazz);
    }
}
