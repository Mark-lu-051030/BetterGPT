package interface_adapter;

import app.ApiKeyProvider;
import data_access.ConversationRepository;
import data_access.FirebaseConversationsRepository;
import data_access.GptApiClient;
import data_access.UserRepository;
import entity.Conversation;
import use_case.ChatService;
import use_case.LoadConversations;
import view.ChatWindow;

import javax.swing.*;
import java.util.Arrays;

/**
 * The ApplicationInitializer class is responsible for setting up and starting the application.
 * It initializes necessary services and components, such as the GPT API client, conversation repository,
 * and chat service, and links them to the chat window.
 */
public class ApplicationInitializer {

    /**
     * Starts the application by initializing the required services and linking them with the chat window.
     *
     * @param username      the username of the user starting the application
     * @param chatWindow    the ChatWindow instance that serves as the user interface
     * @param userRepository the UserRepository instance for managing user data
     */
    public static void start(String username, ChatWindow chatWindow, UserRepository userRepository) {
        // Retrieve API key and initialize GPT API client
        String apiKey = ApiKeyProvider.getApiKey("SERVICE_OPENAI");
        GptApiClient gptApiClient = new GptApiClient(apiKey);

        // Initialize the conversation repository
        ConversationRepository conversationRepository = new FirebaseConversationsRepository();

        // Load existing conversations (currently commented out)
//        LoadConversations loadConversations = new LoadConversations(conversationRepository);
//        ConversationController conversationController = new ConversationController(loadConversations);
//        SwingUtilities.invokeLater(() -> {
//            String[] conversationArray = conversationController.loadUserConversations(username);
//            System.out.println(Arrays.toString(conversationArray));
//            chatWindow.setConversationListData(conversationArray);
//        });

        // Create a new conversation and add it to the repository
        Conversation conversation = new Conversation(username);
        conversationRepository.addConversation(conversation);

        // Link the conversation to the user in the repository
        userRepository.addConversationIdToUser(username, conversation.getId());

        // Initialize the chat service and controller
        ChatService chatService = new ChatService(gptApiClient, conversationRepository, conversation);
        ChatController chatController = new ChatController(chatService);

        // Set up event listeners for user input in the chat window
        chatWindow.addInputFieldListener(e -> {
            String userInput = chatWindow.getInputText();
            if (!userInput.isEmpty()) {
                // Display user input in the chat window
                chatWindow.appendChatMessage("You: " + userInput);
                chatWindow.clearInputField();

                // Get the response from the chat service and display it
                String response = chatController.getResponse(userInput);
                chatWindow.appendChatMessage("Assistant: " + response);
            }
        });
    }
}
