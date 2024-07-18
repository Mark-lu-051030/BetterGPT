package interface_adapter;

import use_case.ChatService;

public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    public String getResponse(String prompt) {
        return chatService.generateResponse(prompt);
    }
}