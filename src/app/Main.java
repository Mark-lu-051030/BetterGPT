package app;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import data_access.ConversationRepository;
import data_access.FirebaseConversationsRepository;
import app.FirebaseInitializer;
import data_access.FirebaseUserRepository;
import data_access.GptApiClient;
import entity.Conversation;
import entity.Message;
import entity.User;
import interface_adapter.ChatController;
import use_case.ChatService;
import use_case.SignInService;
import view.ViewManager;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        javax.swing.SwingUtilities.invokeLater(() -> {
            FirebaseUserRepository userRepository = new FirebaseUserRepository();
            FirebaseConversationsRepository conversationsRepository = new FirebaseConversationsRepository();

            // Sign in the user
            SignInService.signIn("testuser", "password123", userRepository, new SignInService.SignInCallback() {
                @Override
                public void onSignInResult(boolean success, String message) {
                    if (success) {
                        System.out.println("User signed in successfully!");

                        // Fetch user conversations
                        userRepository.findByUsername("testuser", user -> {
                            if (user != null && user.getConversationIds() != null && !user.getConversationIds().isEmpty()) {
                                // Load the first conversation for demonstration
                                String conversationId = user.getConversationIds().get(0);
                                conversationsRepository.getConversationById(conversationId, new ConversationRepository.ConversationCallback() {
                                    @Override
                                    public void onCallback(Conversation conversation) {
                                        if (conversation != null) {
                                            initializeChat(conversation, userRepository, conversationsRepository);
                                        } else {
                                            // Start a new conversation if none exist
                                            Conversation newConversation = new Conversation();
                                            newConversation.setUsername("testuser");
                                            newConversation.setCreationTime(LocalDateTime.now());
                                            newConversation.setModificationTime(LocalDateTime.now());
                                            conversationsRepository.addConversation(newConversation);
                                            userRepository.addConversationIdToUser("testuser", newConversation.getId());
                                            initializeChat(newConversation, userRepository, conversationsRepository);
                                        }
                                    }
                                });
                            } else {
                                // Start a new conversation if none exist
                                Conversation newConversation = new Conversation();
                                newConversation.setUsername("testuser");
                                newConversation.setCreationTime(LocalDateTime.now());
                                newConversation.setModificationTime(LocalDateTime.now());
                                conversationsRepository.addConversation(newConversation);
                                userRepository.addConversationIdToUser("testuser", newConversation.getId());
                                initializeChat(newConversation, userRepository, conversationsRepository);
                            }
                        });
                    } else {
                        System.out.println("User sign-in failed: " + message);
                    }
                }
            });
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