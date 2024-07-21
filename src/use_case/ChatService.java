package use_case;

import data_access.GptApiClient;

/**
 * ChatService handles the business logic for generating chat responses.
 */
public class ChatService {
    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Generates a response from a API based on the user's input.
     *
     * @param userInput the user's input for the chat service
     * @return the response generated by API
     */
    public String generateResponse(String userInput) {
        return chatClient.getChatCompletion(userInput);
    }
}