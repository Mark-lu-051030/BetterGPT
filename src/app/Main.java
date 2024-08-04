package app;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import data_access.FirebaseConversationsRepository;
import data_access.FirebaseUserRepository;
import use_case.SignInService;
import view.ViewManager;
import data_access.GptApiClient;
import interface_adapter.ChatController;
import use_case.ChatService;
import entity.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        FirebaseInitializer.initialize();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FirebaseUserRepository userRepository = new FirebaseUserRepository();

                /* sign in testing
                SignInService.signIn("Mar", "", userRepository, new SignInService.SignInCallback() {
                    @Override
                    public void onSignInResult(boolean success, String message) {
                        System.out.println(message);
                        if (success) {
                            System.out.println("User signed in successfully!");
                        } else {
                            System.out.println("User sign-in failed!");
                        }
                    }
                });

                //converstaion storing testing:

                FirebaseConversationsRepository conversationsRepository = new FirebaseConversationsRepository();
                Conversation conversation = new Conversation();
                conversation.setUsername("testuser");
                conversation.setCreationTime(LocalDateTime.now());
                conversation.setModificationTime(LocalDateTime.now());

                // Add a conversation
                conversationsRepository.addConversationWithListener(conversation);
                userRepository.addConversationIdToUser("testuser", conversation.getId());


                // Add a message to the conversation
                Message message = new Message();
                message.setContent("Hello, this is a test message!");
                message.setTimestamp(LocalDateTime.now());
                message.setRole("user");

                conversationsRepository.addMessage("-O3LLeMWamyWV3sISDdg", message); */

                String apiKey = ApiKeyProvider.getApiKey("SERVICE_OPENAI");
                GptApiClient gptApiClient = new GptApiClient(apiKey);
                ChatService chatService = new ChatService(gptApiClient);
                ChatController chatController = new ChatController(chatService);

                new ViewManager(chatController);
            }
        });
    }
}

