package app;

import data_access.*;
import entity.Conversation;
import interface_adapter.ChatController;
import interface_adapter.LoginController;
import use_case.ChatService;
import view.LoginView;
import view.prechat.ViewManager;

public class Main {
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        FirebaseUserRepository userRepository = new FirebaseUserRepository();

        LoginView loginView = new LoginView();
        new LoginController(loginView, userRepository);

        loginView.setVisible(true);
    }

    private static void initializeChat(Conversation conversation, FirebaseUserRepository userRepository, FirebaseConversationsRepository conversationsRepository) {
        String apiKey = ApiKeyProvider.getApiKey("SERVICE_OPENAI");
        GptApiClient gptApiClient = new GptApiClient(apiKey);
        ChatService chatService = new ChatService(gptApiClient, conversationsRepository, conversation);
        ChatController chatController = new ChatController(chatService);

        new ViewManager(chatController);
    }
}
