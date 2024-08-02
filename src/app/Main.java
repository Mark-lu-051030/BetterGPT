package app;

import data_access.FirebaseUserRepository;
import view.ViewManager;
import data_access.GptApiClient;
import interface_adapter.ChatController;
import use_case.ChatService;
import data_access.SQLiteUserRepository;
import entity.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FirebaseUserRepository userRepository = new FirebaseUserRepository();

                User newUser = new User("testuser", "password123", "testuser@example.com");
                userRepository.addUser(newUser);

                String apiKey = ApiKeyProvider.getApiKey("SERVICE_OPENAI");
                GptApiClient gptApiClient = new GptApiClient(apiKey);
                ChatService chatService = new ChatService(gptApiClient);
                ChatController chatController = new ChatController(chatService);

                new ViewManager(chatController);
            }
        });
    }
}

