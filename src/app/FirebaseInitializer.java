package app;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * The FirebaseInitializer class is responsible for initializing the Firebase SDK
 * with the required credentials and database URL.
 */
public class FirebaseInitializer {

    /**
     * Initializes Firebase with the provided service account key and database URL.
     * This method decodes the service account key, creates a FirebaseOptions object,
     * and initializes the FirebaseApp instance.
     *
     * @throws RuntimeException if the initialization fails due to an IOException
     */
    public static void initialize() {
        try {
            // Retrieve and decode the service account key
            byte[] decodedServiceAccountKey = ApiKeyProvider.getDecodedApiKey("SERVICE_FIREBASE");

            // Convert the decoded key to an input stream
            ByteArrayInputStream serviceAccount = new ByteArrayInputStream(decodedServiceAccountKey);

            // Configure Firebase with the credentials and database URL
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://bettergpt-19b1d-default-rtdb.firebaseio.com/")
                    .build();

            // Initialize the Firebase application
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }
}
