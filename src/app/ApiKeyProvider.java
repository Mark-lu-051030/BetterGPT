package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * The ApiKeyProvider class is responsible for reading, storing, and providing
 * access to API keys that are stored in an encrypted file.
 * It decodes the keys as needed for use in the application.
 */
public class ApiKeyProvider {
    private static final String API_KEY_FILE_PATH = "src/resources/apikeys.enc";
    private static final Map<String, String> API_KEYS = new HashMap<>();

    // Static block to load and decode API keys when the class is loaded
    static {
        try {
            String encodedApiKey = new String(Files.readAllBytes(Paths.get(API_KEY_FILE_PATH)));
            String[] lines = encodedApiKey.split("\n");
            for (String line : lines) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    API_KEYS.put(parts[0], parts[1].trim());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read API key from file", e);
        }
    }

    /**
     * Retrieves and decodes the API key for the specified service.
     *
     * @param serviceName the name of the service for which the API key is requested
     * @return the decoded API key as a String
     * @throws IllegalArgumentException if the API key for the specified service is not found
     */
    public static String getApiKey(String serviceName) {
        return new String(Base64.getDecoder().decode(API_KEYS.get(serviceName)));
    }

    /**
     * Retrieves and decodes the API key for the specified service as a byte array.
     *
     * @param serviceName the name of the service for which the API key is requested
     * @return the decoded API key as a byte array
     * @throws IllegalArgumentException if the API key for the specified service is not found
     */
    public static byte[] getDecodedApiKey(String serviceName) {
        String encodedKey = API_KEYS.get(serviceName);
        if (encodedKey != null) {
            return Base64.getDecoder().decode(encodedKey);
        }
        throw new IllegalArgumentException("No API key found for service: " + serviceName);
    }
}
