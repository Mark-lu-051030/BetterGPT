package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ApiKeyProvider {
    private static final String API_KEY_FILE_PATH = "apikeys.enc";

    public static String getApiKey() {
        try {
            String encodedApiKey = new String(Files.readAllBytes(Paths.get(API_KEY_FILE_PATH)));
            byte[] decodedBytes = Base64.getDecoder().decode(encodedApiKey);
            return new String(decodedBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read API key from file", e);
        }
    }
}
