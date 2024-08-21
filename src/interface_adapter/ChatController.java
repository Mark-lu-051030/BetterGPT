package interface_adapter;

import com.theokanning.openai.completion.chat.ChatMessage;
import use_case.ChatService;

import java.util.List;

/**
 * ChatController manages the interaction between the user and the chat service.
 */
public class ChatController {
    private final ChatService chatService;


    /**
     * Constructs a new ChatController with the specified ChatService.
     * @param chatService the ChatService used to generate responses
     */
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Gets the response from the chat service based on the user's prompt.
     * @param userInput the user's input
     * @return the response generated by the chat service
     */
    public String getResponse(String userInput) {
        return chatService.handleUserInput(userInput);
    }
}
