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

public class ApplicationInitializer {
    public static void start(String username, ChatWindow chatWindow, UserRepository userRepository) {
        String apiKey = ApiKeyProvider.getApiKey("SERVICE_OPENAI");
        GptApiClient gptApiClient = new GptApiClient(apiKey);
        ConversationRepository conversationRepository = new FirebaseConversationsRepository();

        Conversation conversation = new Conversation(username);
        conversationRepository.addConversation(conversation);
        userRepository.addConversationIdToUser(username, conversation.getId());

        ChatService chatService = new ChatService(gptApiClient, conversationRepository, conversation);
        ChatController chatController = new ChatController(chatService);


        // Link the chat window with the chat controller
        chatWindow.addInputFieldListener(e -> {
            String userInput = chatWindow.getInputText();
            if (!userInput.isEmpty()) {
                chatWindow.appendChatMessage("You: " + userInput);
                chatWindow.clearInputField();
                String response = chatController.getResponse(userInput);
                chatWindow.appendChatMessage("Assistant: " + response);

            }
        });
    }
}
