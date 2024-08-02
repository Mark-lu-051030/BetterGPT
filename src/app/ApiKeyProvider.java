package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ApiKeyProvider {
    private static final String API_KEY_FILE_PATH = "src/resources/apikeys.enc";
    private static final Map<String, String> API_KEYS = new HashMap<>();

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

    public static String getApiKey(String serviceName) {
        return new String(Base64.getDecoder().decode(API_KEYS.get(serviceName)));
    }

    public static byte[] getDecodedApiKey(String serviceName) {
        String encodedKey = API_KEYS.get(serviceName);
        if (encodedKey != null) {
            return Base64.getDecoder().decode(encodedKey);
        }
        throw new IllegalArgumentException("No API key found for service: " + serviceName);
    }
}
