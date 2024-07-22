package app;

import view.ViewManager;
import data_access.GptApiClient;
import interface_adapter.ChatController;
import use_case.ChatService;
import data_access.SQLiteUserRepository;
import entity.*;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SQLiteUserRepository userRepository = new SQLiteUserRepository();

                String apiKey = ApiKeyProvider.getApiKey();
                GptApiClient gptApiClient = new GptApiClient(apiKey);
                ChatService chatService = new ChatService(gptApiClient);
                ChatController chatController = new ChatController(chatService);

                new ViewManager(chatController);
            }
        });
    }
}

