package app;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FirebaseInitializer {

    public static void initialize() {
        try {
            byte[] decodedServiceAccountKey = ApiKeyProvider.getDecodedApiKey("SERVICE_FIREBASE");

            ByteArrayInputStream serviceAccount = new ByteArrayInputStream(decodedServiceAccountKey);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://bettergpt-19b1d-default-rtdb.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
