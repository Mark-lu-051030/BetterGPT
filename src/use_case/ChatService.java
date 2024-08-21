package use_case;

import com.theokanning.openai.completion.chat.ChatMessage;
import data_access.GptApiClient;
import data_access.ConversationRepository;
import data_access.UserRepository;
import entity.Conversation;
import entity.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ChatService {
    private final GptApiClient gptApiClient;
    private final ConversationRepository conversationRepository;
    private final Conversation conversation;

    public ChatService(GptApiClient gptApiClient, ConversationRepository conversationRepository, Conversation conversation) {
        this.gptApiClient = gptApiClient;
        this.conversationRepository = conversationRepository;
        this.conversation = conversation;
    }


    public String handleUserInput(String userInput) {
        // Add user's input message to the conversation
        Message userMessage = new Message(null, conversation.getId(), userInput, LocalDateTime.now(), "user");
        conversation.addMessage(userMessage);
        conversationRepository.addMessage(conversation.getId(), userMessage);

        // Prepare the conversation history
        List<ChatMessage> conversationHistory = conversation.getMessages().stream()
                .map(msg -> new ChatMessage(msg.getRole(), msg.getContent()))
                .collect(Collectors.toList());

        // Get response from OpenAI
        String response = gptApiClient.getChatCompletion(conversationHistory);

        // Add the response message to the conversation
        Message botMessage = new Message(null, conversation.getId(), response, LocalDateTime.now(), "assistant");
        conversation.addMessage(botMessage);
        conversationRepository.addMessage(conversation.getId(), botMessage);

        return response;
    }
}
