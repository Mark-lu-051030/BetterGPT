package app;

import data_access.*;
import entity.Conversation;
import interface_adapter.ChatController;
import use_case.ChatService;
import view.ViewManager;

import javax.swing.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        javax.swing.SwingUtilities.invokeLater(() -> {
            FirebaseUserRepository userRepository = new FirebaseUserRepository();
            FirebaseConversationsRepository conversationsRepository = new FirebaseConversationsRepository();

            Conversation newConversation = new Conversation();
            newConversation.setUsername("testuser");
            newConversation.setCreationTime(LocalDateTime.now());
            newConversation.setModificationTime(LocalDateTime.now());

            conversationsRepository.addConversation(newConversation);

            userRepository.addConversationIdToUser("testuser", newConversation.getId());

            initializeChat(newConversation, userRepository, conversationsRepository);
        });
    }

    private static void initializeChat(Conversation conversation, FirebaseUserRepository userRepository, FirebaseConversationsRepository conversationsRepository) {
        String apiKey = ApiKeyProvider.getApiKey("SERVICE_OPENAI");
        GptApiClient gptApiClient = new GptApiClient(apiKey);
        ChatService chatService = new ChatService(gptApiClient, conversationsRepository, conversation);
        ChatController chatController = new ChatController(chatService);

        new ViewManager(chatController);
    }
}
